package br.com.fipeApiHttpRest.models;

public record Veiculo(int TipoVeiculo,
                      String Valor,
                      String Marca,
                      String Modelo,
                      int AnoModelo,
                      String Combustivel,
                      String CodigoFipe,
                      String MesReferencia,
                      char SiglaCombustivel
                      ) {
}
