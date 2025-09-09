package com.empresa.gui;

import com.empresa.controller.FuncionarioController;
import javax.swing.*;
import java.awt.*;

public class FuncionarioGUI extends JFrame {

    private final JTextArea areaDeTexto;
    private final FuncionarioController controller;

    public FuncionarioGUI(FuncionarioController controller) {
        this.controller = controller;

        setTitle("Teste Prático Iniflex");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(0, 1, 5, 5));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaDeTexto = new JTextArea();
        areaDeTexto.setEditable(false);
        areaDeTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        // btns
        JButton btnCarregarDados = new JButton("3.1 - Inserir");
        btnCarregarDados.addActionListener(e -> controller.carregarReiniciarDados());
        painelBotoes.add(btnCarregarDados);

        JButton btnRemoverJoao = new JButton("3.2 Remover Joao");
        btnRemoverJoao.addActionListener(e -> controller.removerJoao());
        painelBotoes.add(btnRemoverJoao);

        JButton btnImprimirTodos = new JButton("3.3 - Imprimir Todos");
        btnImprimirTodos.addActionListener(e -> controller.imprimirTodos());
        painelBotoes.add(btnImprimirTodos);

        JButton btnAumento = new JButton("3.4 - Aplicar Aumento");
        btnAumento.addActionListener(e -> controller.aplicarAumento());
        painelBotoes.add(btnAumento);

        JButton btnAgrupar = new JButton("3.5/3.6 - Agrupar por Função");
        btnAgrupar.addActionListener(e -> controller.agruparEImprimirPorFuncao());
        painelBotoes.add(btnAgrupar);

        JButton btnAniversariantes = new JButton("3.8 - Imprimir Aniversariantes");
        btnAniversariantes.addActionListener(e -> controller.imprimirAniversariantes());
        painelBotoes.add(btnAniversariantes);

        JButton btnMaisVelho = new JButton("3.9 - Imprimir Mais Velho");
        btnMaisVelho.addActionListener(e -> controller.imprimirMaisVelho());
        painelBotoes.add(btnMaisVelho);

        JButton btnOrdemAlfabetica = new JButton("3.10 - Imprimir Ordem Alfabética");
        btnOrdemAlfabetica.addActionListener(e -> controller.imprimirOrdemAlfabetica());
        painelBotoes.add(btnOrdemAlfabetica);

        JButton btnTotalSalarios = new JButton("3.11 - Imprimir Total Salários");
        btnTotalSalarios.addActionListener(e -> controller.imprimirTotalSalarios());
        painelBotoes.add(btnTotalSalarios);

        JButton btnSalariosMinimos = new JButton("3.12 - Imprimir Salários Mínimos");
        btnSalariosMinimos.addActionListener(e -> controller.imprimirSalariosMinimos());
        painelBotoes.add(btnSalariosMinimos);

        add(painelBotoes, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void atualizarTexto(String texto) {
        areaDeTexto.setText(texto);
    }
}