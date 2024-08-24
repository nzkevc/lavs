package uoa.lavs.controllers.cards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import uoa.lavs.models.LoanPaymentRow;
import uoa.lavs.models.LoanPayments;

public class LoanPaymentsCardController extends ICard<LoanPayments>{

    @FXML private MFXPaginatedTableView<LoanPaymentRow> loanPaymentsTable;

    @Override
    public void render(LoanPayments loanPayments) {
        
        setupTable(loanPayments);
        loanPaymentsTable.autosizeColumnsOnInitialization();

		When.onChanged(loanPaymentsTable.currentPageProperty())
				.then((oldValue, newValue) -> loanPaymentsTable.autosizeColumns())
				.listen();

    }

    private void setupTable(LoanPayments loanPayments) {
        List<Double> paymentInterests = loanPayments.getPaymentInterests();
        List<Double> paymentPrincipals = loanPayments.getPaymentPrincipals();
        List<Double> paymentRemainings = loanPayments.getPaymentRemainings();
        List<Integer> paymentNumbers = loanPayments.getPaymentNumbers();

        List<LoanPaymentRow> tableRows = new ArrayList<>();
        for (int i = 0; i < paymentNumbers.size(); i++) {
            tableRows.add(new LoanPaymentRow(
                paymentInterests.get(i),
                paymentPrincipals.get(i),
                paymentRemainings.get(i),
                paymentNumbers.get(i)
            ));
        }
        MFXTableColumn<LoanPaymentRow> interestColumn = new MFXTableColumn<>("Interest", false, Comparator.comparing(LoanPaymentRow::getPaymentInterest));
        MFXTableColumn<LoanPaymentRow> principalColumn = new MFXTableColumn<>("Principal", false, Comparator.comparing(LoanPaymentRow::getPaymentPrincipal));
        MFXTableColumn<LoanPaymentRow> remainingColumn = new MFXTableColumn<>("Remaining", false, Comparator.comparing(LoanPaymentRow::getPaymentRemaining));
        MFXTableColumn<LoanPaymentRow> numberColumn = new MFXTableColumn<>("Number", false, Comparator.comparing(LoanPaymentRow::getPaymentNumber));

        interestColumn.setRowCellFactory(payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentInterest));
        principalColumn.setRowCellFactory(payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentPrincipal));
        remainingColumn.setRowCellFactory(payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentRemaining));
        numberColumn.setRowCellFactory(payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentNumber));

        loanPaymentsTable.getTableColumns().addAll(interestColumn, principalColumn, remainingColumn, numberColumn);
        loanPaymentsTable.getFilters().addAll(
            new DoubleFilter<>("Interest", LoanPaymentRow::getPaymentInterest),
            new DoubleFilter<>("Principal", LoanPaymentRow::getPaymentPrincipal),
            new DoubleFilter<>("Remaining", LoanPaymentRow::getPaymentRemaining),
            new IntegerFilter<>("Number", LoanPaymentRow::getPaymentNumber)
        );
        loanPaymentsTable.setItems(FXCollections.observableArrayList(tableRows));
    }

    @Override
    public void clear() {
        loanPaymentsTable.getItems().clear();
    }

    @Override
    public LoanPayments assemble() {
        return null;
    }
}
