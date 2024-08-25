package uoa.lavs.models;

public class LoanPaymentRow implements IModel<LoanPaymentRow> {
    private final double paymentInterest;
    private final double paymentPrincipal;
    private final double paymentRemaining;
    private final int paymentNumber;

    public LoanPaymentRow(double paymentInterest, double paymentPrincipal, double paymentRemaining, int paymentNumber) {
        this.paymentInterest = paymentInterest;
        this.paymentPrincipal = paymentPrincipal;
        this.paymentRemaining = paymentRemaining;
        this.paymentNumber = paymentNumber;
    }

    public double getPaymentInterest() {
        return paymentInterest;
    }

    public double getPaymentPrincipal() {
        return paymentPrincipal;
    }

    public double getPaymentRemaining() {
        return paymentRemaining;
    }

    public int getPaymentNumber() {
        return paymentNumber;
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }
}

