package Controlador;

import Modelo.Modelo;
import Vista.VistaPalindromo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    private VistaPalindromo view;
    private Modelo model;

    public Controlador(VistaPalindromo view, Modelo model) {
        this.view = view;
        this.model = model;
        this.view.startBtn.addActionListener(this);
    }

    public void startView() {
        view.setTitle("AUTOMATA DE PILA");
        view.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String data = view.dataTxtField.getText();

        String[] cadenaEntrada = new String[data.length() + 2];
        cadenaEntrada[0] = data;

        model.setCadenaEntrada(cadenaEntrada);

        if (model.getPalindrome() && !model.getImpar() && !model.getOtroCaracter()) {
            //Manejar PorLeer y Pila
            model.caso1();

            //Agregar filas a la tabla
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Estado");
            tableModel.addColumn("Por Leer");
            tableModel.addColumn("Pila");
            for (int i = 0; i < data.length() + 2; i++) {
                tableModel.addRow(new Object[]{model.getEstados()[i], model.getCadenaEntrada()[i], model.getPila()[i]});
            }
            view.table.setModel(tableModel);
        } else if(!model.getImpar() && !model.getOtroCaracter()){
            //Manejar las columnas cuando la cadena de entrada si es par, si son 'a' y 'b' per no es un palindromo
            model.caso2();
            
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Estado");
            tableModel.addColumn("Por Leer");
            tableModel.addColumn("Pila");
            for (int i = 0; i < data.length() + 2; i++) {
                tableModel.addRow(new Object[]{model.getEstados()[i], model.getCadenaEntrada()[i], model.getPila()[i]});
            }
            view.table.setModel(tableModel);
        }
        else if(model.getImpar() || model.getOtroCaracter()){
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Estado");
            tableModel.addColumn("Por Leer");
            tableModel.addColumn("Pila");
            view.table.setModel(tableModel);
        }
        view.resultTxtField.setText(model.getResultado());
    }
}
