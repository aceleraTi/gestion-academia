INSERT INTO nivel_educativo(nombre,descripcion)
values
("educación superior","niveles: pregrado y posgrado")

;


INSERT INTO programa_academico(nombre,descripcion,id_nivel_educativo)
values
('IngenieriaSistemas','es una rama de la ingeniería que tiene el objetivo de implementar u optimizar sistemas complejos a través del estudio de la realidad', 1),
('IngenieriaAgrocologica'
,'La carrera de Ingeniería en Agroecología te permitirá desarrollarte profesionalmente en sistemas agroproductivos y agroalimentarios del país; podrás diagnosticar su sostenibilidad, sus deficiencias y su impacto socioambiental para fortalecerlos, mejorarlos o transformarlos.'
, 1),
('IngenieriaIndustrial',
'La Ingeniería Industrial es la rama que se ocupa de la optimización de procesos y recursos humanos, técnicos e informativos; así como el manejo de los sistemas de producción, llevando a su organización a ser más competitiva y sostenible.'
, 1),
('INGENIERIADEALIMENTOS'
,'aborda las bases teóricas y metodológicas de la producción de materias primas necesarias para la conservación, transformación y comercialización de los productos alimenticios, en función de los mercados.'
,1)
;




INSERT INTO programa_academico(nombre,descripcion,id_nivel_educativo,id_director)
values
('Biologia'
,'Formar profesionales integrales y de calidad, mediante una oferta académica pertinente y de excelencia, basada en la investigación y en la creación de conocimiento científico, con una estrategia pedagógica y curricular flexible.'
, 1,2),
('Arquitectura',
'tiene como principal propósito la formación integral de los estudiantes “dentro de un espíritu creativo” desde el punto de vista técnico y teórico conceptual propios de la disciplina, en tanto constructora de un espacio habitable y estable'
,1,3)
;


INSERT INTO pensum(annio, id_programa_academico)
values
(2022,6),
(2023,1),
(2025,5)
;

INSERT INTO materia (nombre, descripcion, id_pensum)
values
('Calculo I', 'Desarrollar en los estudiantes una estructura lógica de pensamiento para aplicarla en la resolución de problemas de las ciencias e ingenierıas', 1),
('Estadistica I', 'Estadística descriptiva, que permite describir las características que presentan los elementos de una población o de una muestra', 2)
,
('Etica', 'análisis sistemático y crítico de la moralidad, de los factores morales que guían la conducta humana en una determinada práctica o sociedad'
,1)
;


INSERT INTO materia (nombre, descripcion, id_pensum,id_materia_prerequisito)
values
('Calculo II', 'En esta asignatura teórica- práctica se estudia la integral definida, las sucesiones, las series y el cálculo diferencial de n variables', 1,1),
('Calculo III', 'se puede considerar como un curso de cálculo medianamente avanzado, de suma importancia en los programas de Física, Matemática, Ingeniería y economía.'
, 1,3)
;
