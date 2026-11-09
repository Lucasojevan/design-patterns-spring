# Design Patterns com Java e Spring

Implementação do desafio da DIO usando os padrões **Singleton**, **Strategy/Repository** e **Facade**.

## Recursos

- API REST para CRUD de clientes em `/clients`;
- Spring Data JPA + H2 para persistência;
- `ViaCepClient` como fachada para a API ViaCEP;
- reutilização de endereços por CEP;
- respostas HTTP adequadas para recursos não encontrados e CEP inválido.

## Executar

Requer Java 8+ e Maven 3.8+:

```bash
mvn spring-boot:run
```

Exemplo de criação:

```bash
curl -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -d '{"name":"Lucas","address":{"zipCode":"01001000"}}'
```

O console H2 fica disponível em `http://localhost:8080/h2-console`.

