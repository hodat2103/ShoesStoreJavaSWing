//package com.myproject.shoesstore.validation;
//
//import java.awt.event.ActionListener;
//import javax.swing.JComboBox;
//
///**
// *
// * @author Tadaboh;
// */
//public class CheckedComboBox<E extends CheckableItem> extends JComboBox<E> {
//  private boolean keepOpen;
//  private transient ActionListener listener;
//
//  protected CheckedComboBox() {
//    super();
//  }
//  protected CheckedComboBox(ComboBoxModel<E> aModel) {
//    super(aModel);
//  }
//  protected CheckedComboBox(E[] m) {
//    super(m);
//  }
//  @Override public Dimension getPreferredSize() {
//    return new Dimension(200, 20);
//  }
//  @Override public void updateUI() {
//    setRenderer(null);
//    removeActionListener(listener);
//    super.updateUI();
//    listener = e -> {
//      if ((e.getModifiers() & InputEvent.MOUSE_EVENT_MASK) != 0) {
//        updateItem(getSelectedIndex());
//        keepOpen = true;
//      }
//    };
//    setRenderer(new CheckBoxCellRenderer<CheckableItem>());
//    addActionListener(listener);
//    getInputMap(JComponent.WHEN_FOCUSED).put(
//        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "checkbox-select");
//    getActionMap().put("checkbox-select", new AbstractAction() {
//      @Override public void actionPerformed(ActionEvent e) {
//        Accessible a = getAccessibleContext().getAccessibleChild(0);
//        if (a instanceof BasicComboPopup) {
//          BasicComboPopup pop = (BasicComboPopup) a;
//          updateItem(pop.getList().getSelectedIndex());
//        }
//      }
//    });
//  }
//  private void updateItem(int index) {
//    if (isPopupVisible()) {
//      E item = getItemAt(index);
//      item.selected ^= true;
//      removeItemAt(index);
//      insertItemAt(item, index);
//      setSelectedItem(item);
//    }
//  }
//  @Override public void setPopupVisible(boolean v) {
//    if (keepOpen) {
//      keepOpen = false;
//    } else {
//      super.setPopupVisible(v);
//    }
//  }
//}
//}
