INSERT INTO nivel_educativo(nombre,descripcion)
values ('Educación Primaria', 'Escuela primaria (grados 1 a 6)'),
('Educación Secundaria', 'Escuela secundaria (grados 7 a 11)'),
('Educación Superior','Niveles: pregrado y posgrado');


INSERT INTO programa_academico(nombre,descripcion,id_nivel_educativo)
values
('IngenieriaSistemas','Descripcion ingenieria de sistemas, descripcion', 1),
('IngenieriaAgrocologica','Descripcion ingenieria agrocologica, descripcion', 2),
('IngenieriaIndustrial','Descripcion ingenieria industrial, descripcion', 2)

;

INSERT INTO programa_academico(nombre,descripcion,id_nivel_educativo,id_director)
values
('IngenieriaSistemas5','Descripcion ingenieria de sistemas, descripcion', 1,2)
;


INSERT INTO pensum(annio, id_programa_academico)
values
(2022,1),
(2023,1),
(2025,2)
;

INSERT INTO materia (nombre, descripcion, id_pensum)
values
('Calculo 1', 'Matematicas Calculo integral primera parte', 1),
('Estadistica 1', 'Estadistica de datos', 2);


INSERT INTO materia (nombre, descripcion, id_pensum,id_materia_prerequisito)
values
('Calculo 2', 'Matematicas Calculo integral segunda parte', 1,1)
;
