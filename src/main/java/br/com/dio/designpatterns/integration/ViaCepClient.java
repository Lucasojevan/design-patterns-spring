package br.com.dio.designpatterns.integration;

import br.com.dio.designpatterns.model.Address;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public Address findAddress(String zipCode) {
        String normalized = zipCode == null ? "" : zipCode.replaceAll("\\D", "");
        if (!normalized.matches("\\d{8}")) throw new IllegalArgumentException("CEP deve conter 8 dígitos.");
        ViaCepResponse response = restTemplate.getForObject(
            "https://viacep.com.br/ws/{zip}/json/", ViaCepResponse.class, normalized);
        if (response == null || Boolean.TRUE.equals(response.erro)) throw new IllegalArgumentException("CEP não encontrado.");
        Address address = new Address();
        address.setZipCode(normalized);
        address.setStreet(response.logradouro);
        address.setDistrict(response.bairro);
        address.setCity(response.localidade);
        address.setState(response.uf);
        return address;
    }

    public static class ViaCepResponse {
        public String logradouro;
        public String bairro;
        public String localidade;
        public String uf;
        public Boolean erro;
    }
}

