--------------------------------------------------------
-- Archivo creado  - s?bado-setiembre-03-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence FUNCIONALIDAD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "FUNCIONALIDAD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence PERSONA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PERSONA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 45 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence ROL_FUNC_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROL_FUNC_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 64 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence ROL_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROL_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 45 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table FUNCIONALIDAD
--------------------------------------------------------

  CREATE TABLE "FUNCIONALIDAD" 
   (	"ID_FUNCIONALIDAD" NUMBER(*,0), 
	"NOMBRE" VARCHAR2(30 BYTE), 
	"DESCRIPCION" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PERSONA
--------------------------------------------------------

  CREATE TABLE "PERSONA" 
   (	"ID_PERSONA" NUMBER(*,0), 
	"DOCUMENTO" VARCHAR2(20 BYTE), 
	"APELLIDO1" VARCHAR2(20 BYTE), 
	"APELLIDO2" VARCHAR2(20 BYTE), 
	"NOMBRE1" VARCHAR2(20 BYTE), 
	"NOMBRE2" VARCHAR2(20 BYTE), 
	"MAIL" VARCHAR2(50 BYTE), 
	"CLAVE" VARCHAR2(20 BYTE), 
	"FEC_NAC" DATE, 
	"ID_ROL" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ROL
--------------------------------------------------------

  CREATE TABLE "ROL" 
   (	"ID_ROL" NUMBER(*,0), 
	"NOMBRE" VARCHAR2(30 BYTE), 
	"DESCRIPCION" VARCHAR2(255 BYTE), 
	"TIPO" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ROL_FUNCION
--------------------------------------------------------

  CREATE TABLE "ROL_FUNCION" 
   (	"ID_ROL_FUNCION" NUMBER(*,0), 
	"ID_ROL" NUMBER(*,0), 
	"ID_FUNCION" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for View PERS_ROL_VIEW
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "PERS_ROL_VIEW" ("ID_PERSONA", "DOCUMENTO", "APELLIDO1", "APELLIDO2", "NOMBRE1", "NOMBRE2", "MAIL", "CLAVE", "FEC_NAC", "ID_ROL", "ROL", "TIPO_ROL", "DESCRIPCION") AS 
  SELECT id_persona, documento, apellido1, apellido2, nombre1, nombre2, mail, clave, fec_nac, persona.id_rol AS id_rol, rol.nombre as rol, rol.tipo as tipo_rol, rol.descripcion
FROM persona LEFT JOIN rol ON persona.id_rol = rol.id_rol ORDER BY id_persona
;
REM INSERTING into FUNCIONALIDAD
SET DEFINE OFF;
Insert into FUNCIONALIDAD (ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) values ('1','Control de Inventario','Realizar una gesti?n de las existencias de un almac?n.');
Insert into FUNCIONALIDAD (ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) values ('2','Control de Ventas','Ventas son las actividades realizadas para incentivar potenciales clientes a realizar una determinada compra.');
Insert into FUNCIONALIDAD (ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) values ('3','Control de Compras','La compra es la acci?n de obtener algo mediante el pago de una cantidad de dinero.');
Insert into FUNCIONALIDAD (ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) values ('4','Cuenta Corriente','La cuenta corriente bancaria es un contrato entre una persona o empresa y un Banco, mediante la cual el primero deposita dinero.');
Insert into FUNCIONALIDAD (ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) values ('5','Control de Sueldos','El salario o sueldo es la remuneraci?n recibida por una persona como pago por su trabajo. De esta forma, el empleado puede beneficiarse de su contribuci?n.');
REM INSERTING into PERSONA
SET DEFINE OFF;
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('5','46577777','PALOMINO','MARTINEZ','MANUEL','ANTONIO','palominomanuel98@gmail.com','9992',to_date('20/09/22','DD/MM/RR'),null);
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('7','79797979','MARTINEZ','GONZALEZ','ALICIA','JUANA','alicia123@gmail.com','54879',to_date('14/09/22','DD/MM/RR'),'1');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('0','52477954','RAMOS','GONZALEZ','JUAN','MANUEL','juan.ramos@gmail.com','123',to_date('01/09/22','DD/MM/RR'),'3');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('1','56787889','MARTINEZ','GONZALEZ','ALICIA','JUANA','alicia123@gmail.com','54879',to_date('01/09/00','DD/MM/RR'),'4');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('2','45657897','BALLESTERO','AGUILAR','JESUS','MIGUEL','jesus.ballestero@hotmail.com','jesujesu',to_date('12/09/75','DD/MM/RR'),'4');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('3','46577789','PALOMINO','MARTINEZ','MANUEL','ANTONIO','manu.palomino2@gmail.com','54gg',to_date('04/05/80','DD/MM/RR'),'3');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('4','34879845','CEBALLOS','PALMA','ROSA','MARIA','mariarosa@gmail.com','qwerty',to_date('19/09/63','DD/MM/RR'),'1');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('26','54618835','DE LEON','MARTINEZ','MARIO','GABRIEL','mario.deleon@estudiantes.utec.edu.uy','123456',to_date('08/12/03','DD/MM/RR'),'5');
Insert into PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL) values ('25','51236955','BURGOS','BURGOS','BRUNO',null,'pacudeus@gmail.com','pacu',to_date('15/05/02','DD/MM/RR'),'25');
REM INSERTING into ROL
SET DEFINE OFF;
Insert into ROL (ID_ROL,NOMBRE,DESCRIPCION,TIPO) values ('1','CONTADOR','HACE CUENTAS','JEFE_SECCION');
Insert into ROL (ID_ROL,NOMBRE,DESCRIPCION,TIPO) values ('3','VENDEDOR','EL QUE OPERA','OPERADOR_SECCION');
Insert into ROL (ID_ROL,NOMBRE,DESCRIPCION,TIPO) values ('5','PAPELERO','TE HACE LOS PAPELES','OPERADOR_SECCION');
Insert into ROL (ID_ROL,NOMBRE,DESCRIPCION,TIPO) values ('2','SYSTEM','EL SISTEMA','ADMINISTRADOR');
Insert into ROL (ID_ROL,NOMBRE,DESCRIPCION,TIPO) values ('4','ADMIN','ADMINISTRA LA OFICINA','ADMINISTRADOR');
Insert into ROL (ID_ROL,NOMBRE,DESCRIPCION,TIPO) values ('25','REPONEDOR','EL QUE REPONE LAS COSAS','OPERADOR_SECCION');
REM INSERTING into ROL_FUNCION
SET DEFINE OFF;
Insert into ROL_FUNCION (ID_ROL_FUNCION,ID_ROL,ID_FUNCION) values ('2','1','1');
Insert into ROL_FUNCION (ID_ROL_FUNCION,ID_ROL,ID_FUNCION) values ('3','1','2');
Insert into ROL_FUNCION (ID_ROL_FUNCION,ID_ROL,ID_FUNCION) values ('1','3','2');
REM INSERTING into PERS_ROL_VIEW
SET DEFINE OFF;
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('0','52477954','RAMOS','GONZALEZ','JUAN','MANUEL','juan.ramos@gmail.com','123',to_date('01/09/22','DD/MM/RR'),'3','VENDEDOR','OPERADOR_SECCION','EL QUE OPERA');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('1','56787889','MARTINEZ','GONZALEZ','ALICIA','JUANA','alicia123@gmail.com','54879',to_date('01/09/00','DD/MM/RR'),'4','ADMIN','ADMINISTRADOR','ADMINISTRA LA OFICINA');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('2','45657897','BALLESTERO','AGUILAR','JESUS','MIGUEL','jesus.ballestero@hotmail.com','jesujesu',to_date('12/09/75','DD/MM/RR'),'4','ADMIN','ADMINISTRADOR','ADMINISTRA LA OFICINA');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('3','46577789','PALOMINO','MARTINEZ','MANUEL','ANTONIO','manu.palomino2@gmail.com','54gg',to_date('04/05/80','DD/MM/RR'),'3','VENDEDOR','OPERADOR_SECCION','EL QUE OPERA');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('4','34879845','CEBALLOS','PALMA','ROSA','MARIA','mariarosa@gmail.com','qwerty',to_date('19/09/63','DD/MM/RR'),'1','CONTADOR','JEFE_SECCION','HACE CUENTAS');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('5','46577777','PALOMINO','MARTINEZ','MANUEL','ANTONIO','palominomanuel98@gmail.com','9992',to_date('20/09/22','DD/MM/RR'),null,null,null,null);
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('7','79797979','MARTINEZ','GONZALEZ','ALICIA','JUANA','alicia123@gmail.com','54879',to_date('14/09/22','DD/MM/RR'),'1','CONTADOR','JEFE_SECCION','HACE CUENTAS');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('25','51236955','BURGOS','BURGOS','BRUNO',null,'pacudeus@gmail.com','pacu',to_date('15/05/02','DD/MM/RR'),'25','REPONEDOR','OPERADOR_SECCION','EL QUE REPONE LAS COSAS');
Insert into PERS_ROL_VIEW (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,MAIL,CLAVE,FEC_NAC,ID_ROL,ROL,TIPO_ROL,DESCRIPCION) values ('26','54618835','DE LEON','MARTINEZ','MARIO','GABRIEL','mario.deleon@estudiantes.utec.edu.uy','123456',to_date('08/12/03','DD/MM/RR'),'5','PAPELERO','OPERADOR_SECCION','TE HACE LOS PAPELES');
--------------------------------------------------------
--  DDL for Index PK_FUNCIONALIDAD
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_FUNCIONALIDAD" ON "FUNCIONALIDAD" ("ID_FUNCIONALIDAD") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_PERSONA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_PERSONA" ON "PERSONA" ("ID_PERSONA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ROL
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ROL" ON "ROL" ("ID_ROL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ROL_FUNCION
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ROL_FUNCION" ON "ROL_FUNCION" ("ID_ROL_FUNCION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UK_NOMBRE
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_NOMBRE" ON "FUNCIONALIDAD" ("NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UK_PERSONA_DOCUMENTO
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_PERSONA_DOCUMENTO" ON "PERSONA" ("DOCUMENTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UK_ROL_FUNCION
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_ROL_FUNCION" ON "ROL_FUNCION" ("ID_ROL", "ID_FUNCION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_FUNCIONALIDAD
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_FUNCIONALIDAD" ON "FUNCIONALIDAD" ("ID_FUNCIONALIDAD") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UK_NOMBRE
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_NOMBRE" ON "FUNCIONALIDAD" ("NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_PERSONA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_PERSONA" ON "PERSONA" ("ID_PERSONA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UK_PERSONA_DOCUMENTO
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_PERSONA_DOCUMENTO" ON "PERSONA" ("DOCUMENTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ROL
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ROL" ON "ROL" ("ID_ROL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ROL_FUNCION
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_ROL_FUNCION" ON "ROL_FUNCION" ("ID_ROL_FUNCION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index UK_ROL_FUNCION
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_ROL_FUNCION" ON "ROL_FUNCION" ("ID_ROL", "ID_FUNCION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table FUNCIONALIDAD
--------------------------------------------------------

  ALTER TABLE "FUNCIONALIDAD" ADD CONSTRAINT "UK_NOMBRE" UNIQUE ("NOMBRE")
  USING INDEX "UK_NOMBRE"  ENABLE;
  ALTER TABLE "FUNCIONALIDAD" MODIFY ("ID_FUNCIONALIDAD" NOT NULL ENABLE);
  ALTER TABLE "FUNCIONALIDAD" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "FUNCIONALIDAD" ADD CONSTRAINT "PK_FUNCIONALIDAD" PRIMARY KEY ("ID_FUNCIONALIDAD")
  USING INDEX "PK_FUNCIONALIDAD"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PERSONA
--------------------------------------------------------

  ALTER TABLE "PERSONA" MODIFY ("ID_PERSONA" NOT NULL ENABLE);
  ALTER TABLE "PERSONA" MODIFY ("DOCUMENTO" NOT NULL ENABLE);
  ALTER TABLE "PERSONA" MODIFY ("APELLIDO1" NOT NULL ENABLE);
  ALTER TABLE "PERSONA" MODIFY ("MAIL" NOT NULL ENABLE);
  ALTER TABLE "PERSONA" MODIFY ("NOMBRE1" NOT NULL ENABLE);
  ALTER TABLE "PERSONA" MODIFY ("CLAVE" NOT NULL ENABLE);
  ALTER TABLE "PERSONA" ADD CONSTRAINT "PK_PERSONA" PRIMARY KEY ("ID_PERSONA")
  USING INDEX "PK_PERSONA"  ENABLE;
  ALTER TABLE "PERSONA" ADD CONSTRAINT "UK_PERSONA_DOCUMENTO" UNIQUE ("DOCUMENTO")
  USING INDEX "UK_PERSONA_DOCUMENTO"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ROL
--------------------------------------------------------

  ALTER TABLE "ROL" MODIFY ("ID_ROL" NOT NULL ENABLE);
  ALTER TABLE "ROL" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "ROL" ADD CONSTRAINT "PK_ROL" PRIMARY KEY ("ID_ROL")
  USING INDEX "PK_ROL"  ENABLE;
  ALTER TABLE "ROL" MODIFY ("TIPO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROL_FUNCION
--------------------------------------------------------

  ALTER TABLE "ROL_FUNCION" MODIFY ("ID_ROL_FUNCION" NOT NULL ENABLE);
  ALTER TABLE "ROL_FUNCION" MODIFY ("ID_ROL" NOT NULL ENABLE);
  ALTER TABLE "ROL_FUNCION" ADD CONSTRAINT "PK_ROL_FUNCION" PRIMARY KEY ("ID_ROL_FUNCION")
  USING INDEX "PK_ROL_FUNCION"  ENABLE;
  ALTER TABLE "ROL_FUNCION" ADD CONSTRAINT "UK_ROL_FUNCION" UNIQUE ("ID_ROL", "ID_FUNCION")
  USING INDEX "UK_ROL_FUNCION"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSONA
--------------------------------------------------------

  ALTER TABLE "PERSONA" ADD CONSTRAINT "PERSONA_FK1" FOREIGN KEY ("ID_ROL")
	  REFERENCES "ROL" ("ID_ROL") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ROL_FUNCION
--------------------------------------------------------

  ALTER TABLE "ROL_FUNCION" ADD CONSTRAINT "FK_FUNCION" FOREIGN KEY ("ID_FUNCION")
	  REFERENCES "FUNCIONALIDAD" ("ID_FUNCIONALIDAD") ON DELETE CASCADE ENABLE;
  ALTER TABLE "ROL_FUNCION" ADD CONSTRAINT "FK_ROL" FOREIGN KEY ("ID_ROL")
	  REFERENCES "ROL" ("ID_ROL") ON DELETE CASCADE ENABLE;
