    # Microservico-authentication/ 
                    **08-06-2025**
                    @Autor: Erik Vilar

    ## Concepts
    
    ### 1 - Domain-Driven-Desgin: Foca em modelar o sofware para corresponder a um dominio de negòcio. O dominio deve ser puro e sem dependência de framework.

    ### 2 - Arquitetura Hexagonal: Isola a lógica principal deste sistema, de detalhes externos como DB outras API's.
        - (Port and Adapters): 
        - (Application & Domain): Contém a lógica de negócio pura.
        - (Ports): São as interfaces que definem como o mundo exterior (Outbound Ports).
        - (Adapters): São as implementações concretos das portas. Um controller REST é um inbound (in) Adapter. Uma         implementação de repositorio com MongoDB e um outbound(out) Adapter.

    ### 3 - Dominio
        - Esta camada e a mais importante, ela nao conhece o spring não conhece o Mongo e nao conhece o REST, ela so conhece as regras de negocio Account
        
        - A essencia de manter o dominio ser anotações como (@Document ou @Id) garante que a lógica de negócio nunca dependerá de como ou onde os dados são armazenados.

        - (Outbound Ports): Dominio precisa salvar buscar e atualizar contas para isso deinimos um contrato (interface) que a camada de persistência deve implementar, chamamos isso de outbound port.

    ### 4 - Application:(Casos de uso)

        - Camada responsável pela lógica de negócio, elá implementa os casos de uso do sistema ex:(CRUD)
           - Porta de entrada (Inbound port):
                    -Define o que a aplicação pode fazer, e um contrato para os adaptadores de entrada (Controllers)
    



