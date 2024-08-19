package uoa.lavs.utility;

import java.time.LocalDate;

public class LoanRepayment {
    private Integer number;
    private LocalDate repaymentDate;
    private Double principal;
    private Double interest;
    private Double remaining;

    public LoanRepayment(Integer number, LocalDate repaymentDate, Double principal, Double interest, Double remaining) {
        this.number = number;
        this.repaymentDate = repaymentDate;
        this.principal = principal;
        this.interest = interest;
        this.remaining = remaining;
    }

    public Integer getNumber() {
        return number;
    }

    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    public Double getPrincipalAmount() {
        return principal;
    }

    public Double getInterestAmount() {
        return interest;
    }

    public Double getRemainingAmount() {
        return remaining;
    }
}
