package com.empresa.main;

import com.empresa.controller.FuncionarioController;
import com.empresa.gui.FuncionarioGUI;
import com.empresa.model.Funcionario;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Funcionario> funcionarios = new ArrayList<>();

            FuncionarioController controller = new FuncionarioController(funcionarios);

            FuncionarioGUI view = new FuncionarioGUI(controller);

            controller.setView(view);

            controller.iniciar();
            view.setVisible(true);
        });
    }
}