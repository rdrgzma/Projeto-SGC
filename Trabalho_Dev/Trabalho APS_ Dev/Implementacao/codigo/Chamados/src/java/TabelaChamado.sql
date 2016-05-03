use database aberturachamados; 
create table chamadosnovosss(
    id int not null auto_increment primary key, 
    nome varchar(20), 
    setor varchar(20), 
    localChamado varchar(20), 
    descricao varchar(120), 
    dataAbertura date,
    email varchar(30)
 ); 



