package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uoa.lavs.models.LoanPaymentRow;
import uoa.lavs.models.LoanPayments;
import uoa.lavs.utils.ControllerUtils;

public class LoanPaymentsCardController extends ICard<LoanPayments> {

  @FXML private Label loanIdLbl;
  @FXML private MFXButton summaryBtn;
  @FXML private MFXPaginatedTableView<LoanPaymentRow> loanTableView;

  public LoanPaymentsCardController() {
    ControllerUtils.loadFxml(this, "cards/loan-payments-card.fxml");
  }

  @Override
  public void render(LoanPayments loanPayments) {

    setupTable(loanPayments);
    loanTableView.autosizeColumnsOnInitialization();

    When.onChanged(loanTableView.currentPageProperty())
        .then((oldValue, newValue) -> loanTableView.autosizeColumns())
        .listen();
  }

  private void setupTable(LoanPayments loanPayments) {
    List<Double> paymentInterests = loanPayments.getPaymentInterests();
    List<Double> paymentPrincipals = loanPayments.getPaymentPrincipals();
    List<Double> paymentRemainings = loanPayments.getPaymentRemainings();
    List<Integer> paymentNumbers = loanPayments.getPaymentNumbers();

    List<LoanPaymentRow> tableRows = new ArrayList<>();
    for (int i = 0; i < paymentNumbers.size(); i++) {
      tableRows.add(
          new LoanPaymentRow(
              paymentInterests.get(i),
              paymentPrincipals.get(i),
              paymentRemainings.get(i),
              paymentNumbers.get(i)));
    }
    MFXTableColumn<LoanPaymentRow> interestColumn =
        new MFXTableColumn<>(
            "Interest", false, Comparator.comparing(LoanPaymentRow::getPaymentInterest));
    MFXTableColumn<LoanPaymentRow> principalColumn =
        new MFXTableColumn<>(
            "Principal", false, Comparator.comparing(LoanPaymentRow::getPaymentPrincipal));
    MFXTableColumn<LoanPaymentRow> remainingColumn =
        new MFXTableColumn<>(
            "Remaining", false, Comparator.comparing(LoanPaymentRow::getPaymentRemaining));
    MFXTableColumn<LoanPaymentRow> numberColumn =
        new MFXTableColumn<>(
            "Number", false, Comparator.comparing(LoanPaymentRow::getPaymentNumber));

    interestColumn.setRowCellFactory(
        payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentInterest));
    principalColumn.setRowCellFactory(
        payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentPrincipal));
    remainingColumn.setRowCellFactory(
        payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentRemaining));
    numberColumn.setRowCellFactory(
        payment -> new MFXTableRowCell<>(LoanPaymentRow::getPaymentNumber));

    loanTableView
        .getTableColumns()
        .addAll(interestColumn, principalColumn, remainingColumn, numberColumn);
    loanTableView
        .getFilters()
        .addAll(
            new DoubleFilter<>("Interest", LoanPaymentRow::getPaymentInterest),
            new DoubleFilter<>("Principal", LoanPaymentRow::getPaymentPrincipal),
            new DoubleFilter<>("Remaining", LoanPaymentRow::getPaymentRemaining),
            new IntegerFilter<>("Number", LoanPaymentRow::getPaymentNumber));
    loanTableView.setItems(FXCollections.observableArrayList(tableRows));
  }

  @Override
  public void clear() {
    loanTableView.getItems().clear();
  }

  @Override
  public LoanPayments assemble() {
    throw new UnsupportedOperationException("Do not assemble LoanSummary");
    }
}
