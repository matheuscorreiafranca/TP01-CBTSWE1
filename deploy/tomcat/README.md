# Publicar a aplicação na raiz do Tomcat

Se o Tomcat mostra `It works !`, ele está servindo a aplicação padrão `ROOT` em vez deste projeto.

## Cenário atual

Sem configuração extra, este projeto normalmente sobe com o contexto do nome da aplicação:

- `http://financeiro.verreschi.com:8002/TP01-CBTSWE1/`

Por isso, acessar apenas:

- `http://financeiro.verreschi.com:8002/`

abre a página padrão do Tomcat.

## Como corrigir

1. Faça o deploy desta aplicação no diretório `webapps` do Tomcat com o nome `TP01-CBTSWE1`.
2. Copie [ROOT.xml](/home/dev/sistema/sistema/TP01-CBTSWE1/deploy/tomcat/ROOT.xml) para `conf/Catalina/localhost/ROOT.xml` no servidor Tomcat.
3. Reinicie o Tomcat.

Com isso, a URL raiz `/` passa a apontar para esta aplicação.

## Alternativa

Se preferir não mexer no `ROOT`, publique o projeto normalmente e acesse pela URL do contexto:

- `http://financeiro.verreschi.com:8002/TP01-CBTSWE1/`
