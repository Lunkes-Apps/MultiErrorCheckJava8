[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.lunkes-apps/MultiErrorCheck/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.lunkes-apps/MultiErrorCheck)

# MultiErrorCheck

Ferramenta para testes automatizados, cuja função é testar diferentes asserções em uma mesma função de teste sem que a exceção de uma impeça a execução das demais.

## Instalação

* Maven
```xml
  <dependency>
    <groupId>io.github.lunkes-apps</groupId>
    <artifactId>MultiErrorCheckJava8</artifactId>
    <version>1.0.0</version>
  </dependency>
```
* Gradle
```groovy
implementation 'io.github.lunkes-apps:MultiErrorCheckJava8:1.0.0'
```

## Exemplo

```java
        //Instancia a classe
        MultiErrorCheck multiErrorCheck = new MultiErrorCheck();
        
        //Adiciona todas as verificações
        multiErrorCheck.addCheck("Os valores não são iguais",1, equalTo(2));
        multiErrorCheck.addCheck(2, greaterThan(3));
        multiErrorCheck.addCheck(false, is(true));

        //Executa todas as verificações
        multiErrorCheck.validateAllErrors();
```

## Asserts
As validações são feitas sempre adicionando um valor atual e uma função Matcher do [Hamcrest](https://hamcrest.org/JavaHamcrest/javadoc/2.2/)