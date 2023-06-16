INSERT INTO nivel_educativo(nombre,descripcion)
values ("educación primaria", " escuela primaria (grados 1 a 6)"),
("educación secundaria"," escuela secundaria (grados 7 a 11)"),
("educación superior","niveles: pregrado y posgrado")

;


INSERT INTO programa_academico(nombre,descripcion,id_nivel_educativo)
values ('IngenieriaSistemas','Descripcion ingenieria de sistemas, descripcion', 1);

INSERT INTO pensum(annio, id_programa_academico)
values
(2022,1),
(2023,1)
;

INSERT INTO materia (nombre, descripcion, id_pensum)
values
("Calculo 1", "Matematicas descripcion testttt", 1),
("Estadistica 1", "Estadistica descripcion testttt", 2)
;