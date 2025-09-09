package com.empresa.controller;

import com.empresa.gui.FuncionarioGUI;
import com.empresa.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioController {

    private final List<Funcionario> funcionarios;
    private FuncionarioGUI view;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public FuncionarioController(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setView(FuncionarioGUI view) {
        this.view = view;
    }

    public void iniciar() {
        view.atualizarTexto("Bem-vindo! Clique em '3.1' para começar.");
    }

    // functions

    //3.1
    public void carregarReiniciarDados() {
        funcionarios.clear();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        view.atualizarTexto("Dados iniciais carregados");
    }

    //3.2
    public void removerJoao(){
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        view.atualizarTexto("Funcionario João removido com sucesso!");
    }

    //3.3
    public void imprimirTodos() {
        if (checkIfEmpty()) return;
        StringBuilder sb = new StringBuilder("--- 3.3 Lista de Todos os Funcionários ---\n\n");
        funcionarios.forEach(f -> sb.append(f.toString()).append("\n"));
        view.atualizarTexto(sb.toString());
    }

    //3.4
    public void aplicarAumento() {
        if (checkIfEmpty()) return;
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        StringBuilder sb = new StringBuilder("--- 3.4 Aumento de 10% Aplicado! ---\n\n");
        funcionarios.forEach(f -> sb.append(f.toString()).append("\n"));
        view.atualizarTexto(sb.toString());
    }

    //3.5 - 3.6
    public void agruparEImprimirPorFuncao() {
        if (checkIfEmpty()) return;
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        StringBuilder sb = new StringBuilder("--- 3.6 Funcionários Agrupados por Função ---\n\n");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            sb.append("Função: ").append(funcao).append("\n");
            lista.forEach(f -> sb.append("  ").append(f.toString()).append("\n"));
            sb.append("\n");
        });
        view.atualizarTexto(sb.toString());
    }

    //3.8
    public void imprimirAniversariantes() {
        if (checkIfEmpty()) return;
        StringBuilder sb = new StringBuilder("--- 3.8 Aniversariantes de Outubro e Dezembro ---\n\n");
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

        if (aniversariantes.isEmpty()) {
            sb.append("Nenhum funcionário faz aniversário em Outubro ou Dezembro.");
        } else {
            aniversariantes.forEach(f -> sb.append(f.toString()).append("\n"));
        }
        view.atualizarTexto(sb.toString());
    }

    //3.9
    public void imprimirMaisVelho() {
        if (checkIfEmpty()) return;
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            String resultado = String.format("--- 3.9 Funcionário com Maior Idade ---\n\nNome: %s\nIdade: %d anos", maisVelho.getNome(), idade);
            view.atualizarTexto(resultado);
        }
    }

    //3.10
    public void imprimirOrdemAlfabetica() {
        if (checkIfEmpty()) return;
        StringBuilder sb = new StringBuilder("--- 3.10 Lista em Ordem Alfabética ---\n\n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> sb.append(f.toString()).append("\n"));
        view.atualizarTexto(sb.toString());
    }

    public void imprimirTotalSalarios() {
        if (checkIfEmpty()) return;
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        view.atualizarTexto("--- 3.11 Total dos Salários ---\n\nO valor total é: R$ " + decimalFormat.format(total));
    }

    //3.11
    public void imprimirSalariosMinimos() {
        if (checkIfEmpty()) return;
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        StringBuilder sb = new StringBuilder("--- 3.12 Relação de Salários Mínimos (Base: R$ 1.212,00) ---\n\n");
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            sb.append(String.format("%-10s ganha %5.2f salários mínimos.\n", f.getNome(), qtdSalarios));
        }
        view.atualizarTexto(sb.toString());
    }

    //3.12
    private boolean checkIfEmpty() {
        if (funcionarios.isEmpty()) {
            view.atualizarTexto("A lista de funcionários está vazia. Carregue os dados primeiro.");
            return true;
        }
        return false;
    }
}