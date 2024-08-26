package uoa.lavs.controllers.cards;

import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.function.Predicate;

public class LocalDateFilter<T> extends TableColumn<T, LocalDate> {
    private ObjectProperty<LocalDate> minDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> maxDate = new SimpleObjectProperty<>();

    public LocalDateFilter(String title, Callback<T, LocalDate> valueExtractor) {
        super(title);
        setCellFactory(column -> new LocalDateFilterCell<>(valueExtractor));
    }

    public ObjectProperty<LocalDate> minDateProperty() {
        return minDate;
    }

    public ObjectProperty<LocalDate> maxDateProperty() {
        return maxDate;
    }

    private class LocalDateFilterCell<S> extends TableCell<S, LocalDate> {
        private Callback<S, LocalDate> valueExtractor;

        public LocalDateFilterCell(Callback<S, LocalDate> valueExtractor) {
            this.valueExtractor = valueExtractor;
        }

        @Override
        protected void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.toString());
        }
    }
}