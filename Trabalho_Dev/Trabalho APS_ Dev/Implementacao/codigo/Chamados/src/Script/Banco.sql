create database if not exists aberturachamados;
use aberturachamados;

select * from setor;
insert into setor (email,localSetor,nomeSetor)values (0,"setor0@email.com", "sala 00", "Setor 0", 0);
insert into setor values (1,"setor1@email.com", "sala 01", "Setor 1", 1);
insert into setor values (2,"setor2@email.com", "sala 02", "Setor 2", 2);
insert into setor values (3,"setor3@email.com", "sala 03", "Setor 3", 3);
insert into setor values (4,"setor4@email.com", "sala 04", "Setor 4", 4);

select * from servidor;
insert into servidor (cargo,email,nome) values ("Servidor","servidor0@email.com", "Servidor 0");
insert into servidor (cargo,email,nome) values ("Servidor","servidor1@email.com", "Servidor 1");
insert into servidor (cargo,email,nome) values ("Servidor","servidor2@email.com", "Servidor 2");
insert into servidor (cargo,email,nome) values ("Servidor","servidor3@email.com", "Servidor 3");
insert into servidor (cargo,email,nome) values ("Servidor","servidor4@email.com", "Servidor 4");

delete from servidor where id in(2,3);

# where id in (5,6);

delete from setor where id in (1,4);

