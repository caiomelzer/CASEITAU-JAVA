# Atividade Diagnóstica

### Funcionalidades!
  - Realizar Cadastro de Usuários
  - Consultar Usuários
  - Consultar Usuário por ID
  - Registrar Ponto de Entrada/Saída
  - Consultar Ponto do Usuário

### Pré-requisitos!
  - JDK
  - Instância MySQL
  - Maven
  
### Instalação

Para realizar a instalação do projeto, siga os passos abaixo

```sh
$ git clone https://github.com/caiomelzer/CASEITAU-JAVA.git
$ cd CASEITAU-JAVA
```
Editar o arquivo /caseitau/src/main/resources/application.properties com a dados da sua instância MySQL
```sh
$ mvn spring-boot:run
```
### Endpoints
[GET] /usuarios
Response:
```sh
[{
    "id": 1,
    "nome_completo": "Caio Melzer",
    "email": "caio@geda.com",
    "cpf": "12345678901",
    "data_cadastro": "2020-02-25T14:19:27.058+0000"
}]
```

[GET] /usuario/{id_usuario}
Response:
```sh
{
    "id": 1,
    "nome_completo": "Caio Melzer",
    "email": "caio@geda.com",
    "cpf": "12345678901",
    "data_cadastro": "2020-02-25T14:19:27.058+0000"
}
```

[POST] /ponto/registrar/{tipo_ponto}
Body:
```sh
{
	"usuario":{
		"id":"1"
	}
}
```
Response:
```sh
{
    "id": 12,
    "data_ponto": "2020-02-25T18:31:14.3115053-03:00",
    "tipo_ponto": "saida",
    "usuario": {
        "id": 1,
        "nome_completo": "Caio Melzer",
        "email": "caio@geda.com",
        "cpf": "12345678901",
        "data_cadastro": "2020-02-25T11:19:27-03:00"
    },
    "minutos_dia": 829
}
```
[GET] /ponto/{id_usuario}
Response:
```sh
{
    "total_horas": 13,
    "relatorioPontos": [
        {
            "id": 12,
            "data_ponto": "2020-02-25T18:31:14-03:00",
            "tipo_ponto": "saida",
            "usuario": {
                "id": 1,
                "nome_completo": "Caio Melzer",
                "email": "caio@geda.com",
                "cpf": "12345678901",
                "data_cadastro": "2020-02-25T11:19:27-03:00"
            },
            "minutos_dia": 829
        },
        {
            "id": 5,
            "data_ponto": "2020-02-25T04:41:16-03:00",
            "tipo_ponto": "entrada",
            "usuario": {
                "id": 1,
                "nome_completo": "Caio Melzer",
                "email": "caio@geda.com",
                "cpf": "12345678901",
                "data_cadastro": "2020-02-25T11:19:27-03:00"
            },
            "minutos_dia": null
        }
    ]
}
```

### Melhorias
 - JWT
 - Login e Senha do Usuário
 - Ajuste do Ponto
