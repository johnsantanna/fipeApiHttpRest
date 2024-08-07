package br.com.fipeApiHttpRest.principal;

import br.com.fipeApiHttpRest.models.Dados;
import br.com.fipeApiHttpRest.models.Modelos;
import br.com.fipeApiHttpRest.models.Veiculo;
import br.com.fipeApiHttpRest.service.ConsumoApi;
import br.com.fipeApiHttpRest.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    //private Scanner leitura = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private int opcao = 0;
    public static int retorno = 0;

    public void exibeMenu() throws InterruptedException {
        var menu = """
                \u001B[32m----TABELA FIPE VERSAO LINHA DE COMANDO----"
                
                **** Opções ****
                
                \u001B[33m(1) Carro
                \u001B[34m(2) Moto
                \u001B[35m(3) Caminhão
                
                \u001B[31m(9) Sair...
                
                \u001B[32mDigite abaixo uma das opções para consultar valores\u001B[0m
                """;
        System.out.print(menu);
        try {
            opcao = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e){
            System.out.println("OCORREU UM ERRO: "+e.getMessage());
            System.out.println("aguarde...");
            Thread.sleep(2000);
            retorno = 0;
            return;
        }
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
            case 9:
                System.out.println("Saindo do programa...");
                Thread.sleep(1000);
                System.out.println("aguarde...");
                Thread.sleep(1000);
                retorno = 1;
                return;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
                System.out.println("aguarde...");
                Thread.sleep(2000);
                retorno = 0;
                return;
        }
        var json = consumo.obterDados(endereco);
        //System.out.println(json);

        List<Dados> marcas = conversor.obterListaDados(json, Dados.class);
        marcas.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta.");
        String codMarca = new Scanner(System.in).nextLine();

        endereco += "/" +  codMarca + "/modelos";
        if (marcas.stream().anyMatch(n -> n.codigo().equals(codMarca))){
            json = consumo.obterDados(endereco);
        } else {
            System.out.println("CODIGO INVALIDO!");
            System.out.println("aguarde...");
            Thread.sleep(2000);
            retorno = 0;
            return;
        }

        Modelos modelos = conversor.obterDados(json, Modelos.class);
        modelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("Informe uma parte do modelo que deseja consultar...");
        String descModelo = new Scanner(System.in).nextLine().toUpperCase();

        modelos.modelos().stream()
                .filter(n -> n.nome().toUpperCase().contains(descModelo))
                .forEach(System.out::println);

        System.out.println("Informe o código do modelo que deseja ver os valores.");
        String codModelo = new Scanner(System.in).nextLine().toUpperCase();

        if (marcas.stream().anyMatch(n -> n.codigo().equals(codMarca))){
            endereco += "/" +  codModelo + "/anos";
            json = consumo.obterDados(endereco);
            List<Dados> anos = conversor.obterListaDados(json, Dados.class);
            List<Veiculo> veiculos = new ArrayList<>();

            for (Dados ano : anos) {
                json = consumo.obterDados(endereco+"/"+ano.codigo());
                veiculos.add(conversor.obterDados(json, Veiculo.class));
            }

            for (Veiculo veiculo : veiculos){
                System.out.println(veiculo);
            }

        } else {
            System.out.println("CODIGO INVALIDO!");
            System.out.println("aguarde...");
            Thread.sleep(2000);
            retorno = 0;
            return;
        }

        //TRATAR AS EXCEÇÕES!...

        retorno = 1;
    }
}

