/*
--------------------------------------------------------------------------------
------------------------------Tipo Horario INSERTS------------------------------
--------------------------------------------------------------------------------
*/
INSERT INTO tipo_horario(nombre, hora_inicio, hora_fin)
    VALUES ('AM',TIMESTAMP '2026-01-01 07:00:00', TIMESTAMP '2026-01-01 15:00:00');
    
INSERT INTO tipo_horario(nombre, hora_inicio, hora_fin)
    VALUES('PM',TIMESTAMP '2026-01-01 15:00:00', TIMESTAMP '2026-01-01 23:00:00');


/*
--------------------------------------------------------------------------------
------------------------------Especialidad INSERTS------------------------------
--------------------------------------------------------------------------------
*/
INSERT INTO especialidades(nombre)
    VALUES ('MEDICINA GENERAL');
INSERT INTO especialidades(nombre)
    VALUES('TRAUMATOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('DERMATOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('CARDIOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('NEUROLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('PEDIATRIA');
INSERT INTO especialidades(nombre)
    VALUES('GINECOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('PSIQUIATRIA');
INSERT INTO especialidades(nombre)
    VALUES('OFTALMOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('OTORRINOLARINGOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('UROLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('ONCOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('ENDOCRINOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('GASTROENTEROLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('NEUMOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('REUMATOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('NEFROLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('INFECTOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('HEMATOLOGIA');
INSERT INTO especialidades(nombre)
    VALUES('CIRUGIA GENERAL');


/*
--------------------------------------------------------------------------------
------------------------------Estado Citas INSERTS------------------------------
--------------------------------------------------------------------------------
*/
INSERT INTO estados_citas(nombre)
    VALUES('CONFIRMADA');
     
/*
--------------------------------------------------------------------------------
----------------------------Plantilla Horario INSERTS---------------------------
--------------------------------------------------------------------------------
*/    
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(15, 1);    
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(15, 2); 
    
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(30, 1);   
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(30, 2);   
    
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(45, 1);   
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(45, 2); 

INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(60, 1); 
INSERT INTO plantilla_horario(duracion_minutos, id_tipo_horario)
    VALUES(60, 2); 




/*
--------------------------------------------------------------------------------
-------------------------------Pacientes INSERTS--------------------------------
--------------------------------------------------------------------------------
*/
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Juan Perez', 'juan.perez@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Maria Gonzalez', 'maria.gonzalez@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Carlos Ramirez', 'carlos.ramirez@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Ana Torres', 'ana.torres@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Luis Fernandez', 'luis.fernandez@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Camila Rojas', 'camila.rojas@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Diego Morales', 'diego.morales@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Valentina Silva', 'valentina.silva@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Javier Castillo', 'javier.castillo@gmail.com');
INSERT INTO pacientes(estado, nombre, email) 
    VALUES (1, 'Fernanda Herrera', 'fernanda.herrera@gmail.com');
    
    
/*
--------------------------------------------------------------------------------
---------------------------------Medicos INSERTS--------------------------------
--------------------------------------------------------------------------------
*/         
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (1, 'Dr. Juan Perez');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (2, 'Dr. Carlos Ramirez');
INSERT INTO medicos (id_especialidad, nombre)
    VALUES (3, 'Dra. Camila Rojas');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (4, 'Dr. Luis Fernandez');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (5, 'Dr. Diego Morales');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (6, 'Dra. Valentina Silva');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (7, 'Dra. Ana Torres');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (8, 'Dr. Javier Castillo');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (9, 'Dra. Fernanda Herrera');
INSERT INTO medicos (id_especialidad, nombre) 
    VALUES (10, 'Dr. Pablo Soto');
    
COMMIT;
