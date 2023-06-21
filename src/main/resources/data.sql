INSERT INTO nivel_educativo(nombre,descripcion)
values ('Educación Primaria', 'Escuela primaria (grados 1 a 6)'),
('Educación Secundaria', 'Escuela secundaria (grados 7 a 11)'),
('Educación Superior','Niveles: pregrado y posgrado');


INSERT INTO programa_academico(nombre,descripcion,id_nivel_educativo)
values
('Ingenieria de Sistemas','Programa de Ingenieria de Sistemas', 3),
('Ingenieria Electronica','Programa de ingenieria electronica', 3),
('Ingenieria Ambiental','Programa de ingenieria ambiental', 2);


INSERT INTO pensum(annio, id_programa_academico)
values
(2022,1),
(2023,1),
(2025,1);

INSERT INTO materia (nombre, descripcion, id_pensum)
values
('Calculo 1', 'Matematicas Calculo integral primera parte', 1),
('Estadistica 1', 'Estadistica de datos', 2);


INSERT INTO materia (nombre, descripcion, id_pensum,id_materia_prerequisito)
values
('Calculo 2', 'Matematicas Calculo integral segunda parte', 1,1)
;