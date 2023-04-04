package com.nttdata.MSPayment.service;

import java.util.Date;

import com.nttdata.MSPayment.MsPaymentApplication;
import com.nttdata.MSPayment.model.Credit;
import com.nttdata.MSPayment.model.Movements;
import com.nttdata.MSPayment.proxy.PaymentProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentProxy paymentProxy = new PaymentProxy();
    private static final Logger logger = LogManager.getLogger(MsPaymentApplication.class);
    @Override
    public Mono<Movements> payCredit(String idCredit, Double amount) {
        logger.info("idCredit: "+idCredit);
        logger.info("idCredit: "+idCredit);

        return paymentProxy.getCredit(idCredit)
                .flatMap(resp->payDebt(resp, amount))
                .flatMap(paymentProxy::updateCredit)
                .flatMap(resp->saveMovement(idCredit, "credit pay", amount, null));

    }


    //AVTIVEPAYMENT UTIL METHODS
    public Mono<Credit> payDebt(Credit credit, Double amount) {
        logger.info("deuda: "+credit.getDebt());
        logger.info("tipo: "+credit.getCreditType());
        logger.info("cardNumber 0: "+credit.getCardNumber());
        logger.info("accountNumber 0: "+credit.getAccountNumber());
        logger.info("idCustomer 0: "+credit.getIdCustomer());
        logger.info("balance 0: "+credit.getBalance());
        logger.info("creditLine 0: "+credit.getCreditLine());
        Double debt = credit.getDebt();
        if(amount < debt) {
            credit.setDebt(debt-amount);
        }else {
            credit.setDebt(0.0);
        }
        return Mono.just(credit);
    }

    public Mono<Movements> saveMovement(String idProduct,
                                     String type,
                                     Double amount,
                                     String idThirdPartyProduct) {

        Movements movement = new Movements();
        movement.setIdProduct(idProduct);
        movement.setType(type);
        movement.setAmount(amount);
        movement.setIdThirdPartyProduct(idThirdPartyProduct);
        movement.setDate(new Date());

        return paymentProxy.saveMovement(movement);
    }
}