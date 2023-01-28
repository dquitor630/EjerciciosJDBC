DROP DATABASE IF EXISTS horario;
CREATE DATABASE horario;
USE horario;

CREATE TABLE ofertaEducativa (
codOe char(3) PRIMARY KEY,
nombre varchar(70),
descripcion varchar(255),
tipo ENUM('CFGS','CFGM'),
fechaLey timestamp);

CREATE TABLE profesor(
codProf char(3) PRIMARY KEY,
nombre varchar(20),
apellidos varchar(40),
fechaAlta date);


CREATE TABLE curso(
codOe char(3),
codCurso char(2),
codTutor char(3) UNIQUE NOT NULL,
PRIMARY KEY(codOe, codCurso),
CONSTRAINT FK_codOe FOREIGN KEY (codOe)
REFERENCES ofertaEducativa(codOe) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_codTutor FOREIGN KEY (codTutor)
REFERENCES profesor(codProf) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE tramoHorario(
codTramo char(2) PRIMARY KEY,
horaInicio TIME,
horaFin TIME,
dia ENUM('LUNES' , 'MARTES' , 'MIERCOLES' , 'JUEVES', 'VIERNES'));

CREATE TABLE asignatura(
codAsig varchar(6) PRIMARY KEY,
nombre varchar(80) NOT NULL,
horasSemanales tinyint unsigned,
horasTotales smallint unsigned
);

CREATE TABLE reparto(
codOe char(3),
codCurso char(2),
codAsig VARCHAR(6),
codProf char(3),
PRIMARY KEY(codOe, codCurso, codAsig),
CONSTRAINT FK_CodOeYCurso FOREIGN KEY (codOe, codCurso)
REFERENCES curso(codOe,codCurso) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodAsig FOREIGN KEY (codAsig)
REFERENCES asignatura(codAsig) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodProf FOREIGN KEY (codProf)
REFERENCES profesor(codProf) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE horario(
codOe char(3),
codCurso char(2),
codAsig varchar(6),
codTramo char(2),
PRIMARY KEY(codOe, codCurso, codAsig, codTramo),
CONSTRAINT FK_CodOeCurso FOREIGN KEY (codOe, codCurso)
REFERENCES Curso(codOe,codCurso) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodAsignatura FOREIGN KEY (codAsig)
REFERENCES Asignatura(codAsig) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodTramo FOREIGN KEY (codTramo)
REFERENCES TramoHorario(codTramo) ON DELETE CASCADE ON UPDATE CASCADE);


INSERT INTO ofertaEducativa VALUES
("SMR","Grado Medio de Sistemas Microinformáticos y Redes","El CFGM SMR tiene una duración de 2000 horas
 repartidas entre dos cursos académicos incluyendo un trimestre de Formacion en centros de trabajo (FCT)
 en empresas del Sector",'CFGM','2009-07-07'),
("DAM","Grado Superior de Desarollo de Aplicaciones Multiplataforma","El CFGs DAM tiene una duración de 2000 horas repartidas entre dos cursos académicos incluyendo 400 horas de Formacion en centros de trabajo (FCT) en empresas del Sector",'CFGS','2011-06-16');

INSERT INTO profesor VALUES
("AGL","Ana","Gutiérrez Lozano",'1999-09-01'),
("PJM","Pedro","Joya Máñez",'2000-09-01'),
("EPM","Eva","Peralta Macías",'2009-09-01'),
("MPR","Monica","Parejo Ramírez",'2022-09-15'),
("PBG","Pilar","Baena García",'2007-09-01'),
("MGD","María","Gallego Díaz",'2016-09-01'),
("SAA","Santiago","Acha Aller",'2018-09-20'),
("ORR","Oscar","Ripoll Romero",'2022-09-15'),
("ADS","Antonio","Díaz Santamaría",'2014-02-11'),
("CVC","Carmelo","Villegas Cruz",'2013-09-01'),
("JLG","Jose","Lobato García",'2022-09-15'),
("CMG","Cristina","Moreno Gonzalez",'2022-09-15'),
("CMS","Cristina","Muñoz Salas",'2022-09-15'),
("ILR","Inmaculada","Lorente Rojas",'2022-09-15');

INSERT INTO curso VALUES
("SMR","1A","CMG"),
("SMR","2A","MGD"),
("DAM","1A","AGL"),
("DAM","2A","CVC");

INSERT INTO asignatura VALUES
("RED", "Redes Locales", 7,224),
("@RED", "Desdoble de Redes Locales", 3,96),
("SISM", "Sistemas operativos monopuestos", 5,160),
("MONT", "Montaje y mantenimiento de equipos", 7,224),
("@MONT", "Desdoble de Montaje y mantenimiento de equipos", 3,96),
("APLI", "Aplicaciones ofimáticas", 8, 256),
("@APLI", "Desdoble de Aplicaciones ofimáticas", 4, 128),
("SEG", "Seguridad informática", 5,105),
("HLC", "Horas de Libre Configuración", 3,63),
("SISR", "Sistemas operativos en red",7,147),
("SERV","Servicios en red",7,147),
("APLIW","Aplicaciones web",4,84),
("EIEM", "Empresa e iniciativa empresarial", 4,84),
("SIST1","Sistemas informáticos 1",3,96),
("SIST2","Sistemas informáticos 2",3,96),
("@SIST","Desdoble de Sistemas informáticos", 3, 96),
("BDD","Bases de Datos", 6, 192),
("@BDD1","Desdoble de Bases de Datos 1", 2, 64),
("@BDD2","Desdoble de Bases de Datos 2", 1, 32),
("PROG","Programación",8, 256),
("@PROG1","Desdoble de Programación 1", 3, 96),
("@PROG2","Desdoble de Programación 2", 3, 96),
("ENT","Entornos de desarrollo",3,96),
("MARC","Lenguajes de marcas y sistemas de gestión de información", 4,128),
("FOL","Formación y orientación laboral",3,96),
("AD","Acceso a datos",5,105),
("DI","Desarrollo de interfaces",7,147),
("PSPRO","Programación de servicios y procesos",3,63),
("PMDMO","Programación multimedia y dispositivos móviles",4,84),
("EIE","Empresa e iniciativa emprendedora",4,84),
("SGE","Sistemas de gestión empresarial",4,84);


INSERT INTO tramoHorario VALUES
("L1", "08:15:00", "09:15:00", 'LUNES'), ("L2", "09:15:00", "10:15:00", 'LUNES'), ("L3", "10:15:00", "11:15:00", 'LUNES'),
("L4", "11:45:00", "12:45:00", 'LUNES'), ("L5", "12:45:00", "13:45:00", 'LUNES'), ("L6", "13:45:00", "14:45:00", 'LUNES'),
("M1", "08:15:00", "09:15:00", 'MARTES'), ("M2", "09:15:00", "10:15:00", 'MARTES'), ("M3", "10:15:00", "11:15:00", 'MARTES'),
("M4", "11:45:00", "12:45:00", 'MARTES'), ("M5", "12:45:00", "13:45:00", 'MARTES'), ("M6", "13:45:00", "14:45:00", 'MARTES'),
("X1", "08:15:00", "09:15:00", 'MIERCOLES'), ("X2", "09:15:00", "10:15:00", 'MIERCOLES'), ("X3", "10:15:00", "11:15:00", 'MIERCOLES'),
("X4", "11:45:00", "12:45:00", 'MIERCOLES'), ("X5", "12:45:00", "13:45:00", 'MIERCOLES'), ("X6", "13:45:00", "14:45:00", 'MIERCOLES'),
("J1", "08:15:00", "09:15:00", 'JUEVES'), ("J2", "09:15:00", "10:15:00", 'JUEVES'), ("J3", "10:15:00", "11:15:00", 'JUEVES'),
("J4", "11:45:00", "12:45:00", 'JUEVES'), ("J5", "12:45:00", "13:45:00", 'JUEVES'), ("J6", "13:45:00", "14:45:00", 'JUEVES'),
("V1", "08:15:00", "09:15:00", 'VIERNES'), ("V2", "09:15:00", "10:15:00", 'VIERNES'), ("V3", "10:15:00", "11:15:00", 'VIERNES'),
("V4", "11:45:00", "12:45:00", 'VIERNES'), ("V5", "12:45:00", "13:45:00", 'VIERNES'), ("V6", "13:45:00", "14:45:00", 'VIERNES');


INSERT INTO reparto VALUES
("SMR","1A","RED","CMS"),
("SMR","1A","@RED","ADS"),
("SMR","1A","SISM","SAA"),
("SMR","1A","MONT","JLG"),
("SMR","1A","@MONT","CVC"),
("SMR","1A","APLI","CMG"),
("SMR","1A","@APLI","SAA"),
("SMR","1A","FOL","ILR"),

("SMR","2A","SEG","AGL"),
("SMR","2A","HLC","PJM"),
("SMR","2A","SISR","PBG"),
("SMR","2A","SERV","MGD"),
("SMR","2A","APLIW","PJM"),
("SMR","2A","EIEM","ILR"),

("DAM","1A","SIST1","CVC"),
("DAM","1A","SIST2","ORR"),
("DAM","1A","@SIST","MPR"),
("DAM","1A","BDD","AGL"),
("DAM","1A","@BDD1","EPM"),
("DAM","1A","@BDD2","PJM"),
("DAM","1A","PROG","EPM"),
("DAM","1A","@PROG1","AGL"),
("DAM","1A","@PROG2","ORR"),
("DAM","1A","ENT","EPM"),
("DAM","1A","MARC","MGD"),
("DAM","1A","FOL","ILR"),

("DAM","2A","AD","EPM"),
("DAM","2A","DI","CVC"),
("DAM","2A","PSPRO","PJM"),
("DAM","2A","PMDMO","PJM"),
("DAM","2A","EIE","ILR"),
("DAM","2A","SGE","JLG"),
("DAM","2A","HLC","MGD");

-- 1 CFGM
INSERT INTO horario VALUES
("SMR", "1A", "RED", "L1"),
("SMR", "1A", "RED", "L2"),
("SMR", "1A", "RED", "L3"),
("SMR", "1A", "@RED", "L3"),
("SMR", "1A", "MONT", "L4"),
("SMR", "1A", "@MONT", "L4"),
("SMR", "1A", "SISM", "L5"),
("SMR", "1A", "SISM", "L6"),

("SMR", "1A", "FOL", "M1"),
("SMR", "1A", "APLI", "M2"),
("SMR", "1A", "@APLI", "M2"),
("SMR", "1A", "APLI", "M3"),
("SMR", "1A", "@APLI", "M3"),
("SMR", "1A", "RED", "M4"),
("SMR", "1A", "RED", "M5"),
("SMR", "1A", "SISM", "M6"),

("SMR", "1A", "APLI", "X1"),
("SMR", "1A", "APLI", "X2"),
("SMR", "1A", "SISM", "X3"),
("SMR", "1A", "APLI", "X4"),
("SMR", "1A", "@APLI", "X4"),
("SMR", "1A", "MONT", "X5"),
("SMR", "1A", "MONT", "X6"),

("SMR", "1A", "APLI", "J1"),
("SMR", "1A", "APLI", "J2"),
("SMR", "1A", "RED", "J3"),
("SMR", "1A", "@RED", "J3"),
("SMR", "1A", "SISM", "J4"),
("SMR", "1A", "MONT", "J5"),
("SMR", "1A", "@MONT", "J5"),
("SMR", "1A", "FOL", "J6"),

("SMR", "1A", "FOL", "V1"),
("SMR", "1A", "MONT", "V2"),
("SMR", "1A", "@MONT", "V2"),
("SMR", "1A", "RED", "V3"),
("SMR", "1A", "@RED", "V3"),
("SMR", "1A", "APLI", "V4"),
("SMR", "1A", "@APLI", "V4"),
("SMR", "1A", "MONT", "V5"),
("SMR", "1A", "MONT", "V6"),


-- 2 CFGM
("SMR","2A","SERV","L1"),
("SMR","2A","SERV","L2"),
("SMR","2A","HLC","L3"),
("SMR","2A","SEG","L4"),
("SMR","2A","EIE","L5"),
("SMR","2A","SISR","L6"),

("SMR","2A","SEG","M1"),
("SMR","2A","SERV","M2"),
("SMR","2A","EIE","M3"),
("SMR","2A","SERV","M4"),
("SMR","2A","APLIW","M5"),
("SMR","2A","SISR","M6"),

("SMR","2A","SISR","X1"),
("SMR","2A","SISR","X2"),
("SMR","2A","SERV","X3"),
("SMR","2A","APLIW","X4"),
("SMR","2A","SEG","X5"),
("SMR","2A","EIE","X6"),

("SMR","2A","SERV","J1"),
("SMR","2A","SERV","J2"),
("SMR","2A","SEG","J3"),
("SMR","2A","APLIW","J4"),
("SMR","2A","HLC","J5"),
("SMR","2A","SISR","J6"),

("SMR","2A","SERV","V1"),
("SMR","2A","SISR","V2"),
("SMR","2A","SISR","V3"),
("SMR","2A","APLIW","V4"),
("SMR","2A","EIE","V5"),
("SMR","2A","SEG","V6"),

-- 1 CFGS
("DAM","1A","ENT","L1"),
("DAM","1A","BDD","L2"),
("DAM","1A","@BDD1","L2"),
("DAM","1A","BDD","L3"),
("DAM","1A","@BDD1","L3"),
("DAM","1A","MARC","L4"),
("DAM","1A","SIST1","L5"),
("DAM","1A","@SIST","L5"),
("DAM","1A","FOL","L6"),

("DAM","1A","SIST1","M1"),
("DAM","1A","PROG","M2"),
("DAM","1A","@PROG2","M2"),
("DAM","1A","PROG","M3"),
("DAM","1A","@PROG2","M3"),
("DAM","1A","SIST2","M4"),
("DAM","1A","@SIST","M4"),
("DAM","1A","MARC","M5"),
("DAM","1A","ENT","M6"),

("DAM","1A","SIST1","X1"),
("DAM","1A","MARC","X2"),
("DAM","1A","FOL","X3"),
("DAM","1A","SIST2","X4"),
("DAM","1A","PROG","X5"),
("DAM","1A","@PROG2","X5"),
("DAM","1A","ENT","X6"),

("DAM","1A","PROG","J1"),
("DAM","1A","PROG","J2"),
("DAM","1A","MARC","J3"),
("DAM","1A","BDD","J4"),
("DAM","1A","BDD","J5"),
("DAM","1A","@BDD2","J5"),
("DAM","1A","BDD","J6"),

("DAM","1A","PROG","V1"),
("DAM","1A","@PROG1","V1"),
("DAM","1A","PROG","V2"),
("DAM","1A","@PROG1","V2"),
("DAM","1A","PROG","V3"),
("DAM","1A","@PROG1","V3"),
("DAM","1A","BDD","V4"),
("DAM","1A","SIST2","V5"),
("DAM","1A","@SIST","V5"),
("DAM","1A","FOL","V6"),

-- 2 CFGS
("DAM","2A","DI","L1"),
("DAM","2A","DI","L2"),
("DAM","2A","HLC","L3"),
("DAM","2A","EIE","L4"),
("DAM","2A","SGE","L5"),
("DAM","2A","PSPRO","L6"),

("DAM","2A","AD","M1"),
("DAM","2A","DI","M2"),
("DAM","2A","HLC","M3"),
("DAM","2A","EIE","M4"),
("DAM","2A","PMDMO","M5"),
("DAM","2A","PMDMO","M6"),

("DAM","2A","SGE","X1"),
("DAM","2A","DI","X2"),
("DAM","2A","AD","X3"),
("DAM","2A","AD","X4"),
("DAM","2A","PMDMO","X5"),
("DAM","2A","PMDMO","X6"),

("DAM","2A","DI","J1"),
("DAM","2A","DI","J2"),
("DAM","2A","PSPRO","J3"),
("DAM","2A","HLC","J4"),
("DAM","2A","EIE","J5"),
("DAM","2A","SGE","J6"),

("DAM","2A","DI","V1"),
("DAM","2A","EIE","V2"),
("DAM","2A","SGE","V3"),
 ("DAM","2A","AD","V4"),
("DAM","2A","AD","V5"),
("DAM","2A","PSPRO","V6");
