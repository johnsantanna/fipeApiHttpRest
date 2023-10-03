package br.com.fipeApiHttpRest.principal;

import br.com.fipeApiHttpRest.models.Dados;
import br.com.fipeApiHttpRest.models.Modelos;
import br.com.fipeApiHttpRest.service.ConsumoApi;
import br.com.fipeApiHttpRest.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        var menu = """
                \u001B[32m----TABELA FIPE VERSAO LINHA DE COMANDO----\u001B[0m"
                
                **** Opções ****
                
                (1) Carro
                (2) Moto
                (3) Caminhão
                
                \u001B[33mDigite abaixo uma das opções para consultar valores\u001B[0m
                """;
        System.out.print(menu);
        int opcao = leitura.nextInt();
        String endereco;

        switch (opcao){
            case 1:
                endereco = URL_BASE + "carros/marcas";
                break;
            case 2:
                endereco = URL_BASE + "motos/marcas";
                break;
            case 3:
                endereco = URL_BASE + "caminhoes/marcas";
                break;
            default:
                endereco = URL_BASE + "carros/marcas";
        }

        var json = consumo.obterDados(endereco);
        //System.out.println(json);

        List<Dados> marcas = conversor.obterListaDados(json, Dados.class);
        marcas.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta.");
        String codMarca = leitura.next();
        endereco += "/" +  codMarca + "/modelos";
        json = consumo.obterDados(endereco);

        Modelos modelos = conversor.obterDados(json, Modelos.class);
        modelos.modelos().stream().sorted(Comparator.comparing(Dados::nome)).forEach(System.out::println);

        System.out.println("Informe uma parte do modelo que deseja consultar...");
        String descModelo = leitura.next();
        String codModelo = modelos.modelos().stream().filter(n -> n.nome().contains(descModelo.toUpperCase())).findFirst().map(Dados::codigo).orElse(null);
        if (codModelo != null){
            endereco += "/" +  codModelo + "/anos";
            json = consumo.obterDados(endereco);
            List<Dados> anos = conversor.obterListaDados(json, Dados.class);
            anos.stream().sorted(Comparator.comparing(Dados::nome)).forEach(System.out::println);
        } else{
            System.out.println("Modelo não encontrado!");
        }

        // DESCOBRIR PORQUE ESTÁ BUSCANDO APENAS 'GLX' INVÉS DE 'GLX 1.4" COM ESPAÇO SEGUIDO DE 1.4


    }
}

