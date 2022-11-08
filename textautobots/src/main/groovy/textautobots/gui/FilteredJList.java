package textautobots.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;

public class FilteredJList extends JList {

    private FilterField filterField;
    private int DEFAULT_FIELD_WIDTH = 20;

    public FilteredJList() {
        setModel(new FilterModel());
        filterField = new FilterField(DEFAULT_FIELD_WIDTH);
    }

    @Override
    public void setModel(ListModel model) {
        if (! (model instanceof FilterModel))
            throw new IllegalArgumentException();

        super.setModel(model);
    }

    public void addItem(Object o) {
        ((FilterModel) getModel()).addElement(o);
    }

    public JTextField getFilterField() {
        return filterField;
    }

    class FilterModel extends AbstractListModel {
        private List items;
        private List filteredItems;

        public FilterModel() {
            items = new ArrayList();
            filteredItems = new ArrayList();
        }

        @Override
        public int getSize() {
            return filteredItems.size();
        }

        @Override
        public Object getElementAt(int index) {
            if (index < filteredItems.size())
                return filteredItems.get(index);

            return null;
        }

        public void addElement(Object o) {
            items.add(o);
            refilter();
        }

        private void refilter() {
            filteredItems.clear();
            String term = getFilterField().getText().toLowerCase();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).toString().toLowerCase().indexOf(term, 0) != -1)
                    filteredItems.add(items.get(i));

                fireContentsChanged(this, 0, getSize());
            }
        }

    }

    class FilterField extends JTextField implements DocumentListener {

        public FilterField (int width) {
            super(width);
            getDocument().addDocumentListener(this);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ((FilterModel) getModel()).refilter();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ((FilterModel) getModel()).refilter();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            ((FilterModel) getModel()).refilter();
        }
    }

}
