--------------------------------------------------------
--  DDL for Sequence SEQ_AUXILIAR_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_AUXILIAR_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_CAMION_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_CAMION_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_CARGA_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_CARGA_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 19 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_CENTROACOPIO_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_CENTROACOPIO_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_CLIENTE_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_CLIENTE_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 5 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_CUENTAS_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_CUENTAS_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 7 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_EMPRESA_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_EMPRESA_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 4 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_PEDIDO_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_PEDIDO_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_TRANSPORTADOR_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_TRANSPORTADOR_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_TRAYECTO_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_TRAYECTO_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_USUARIO_ID
--------------------------------------------------------

   CREATE SEQUENCE   SEQ_USUARIO_ID MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 15 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table CAMION
--------------------------------------------------------

  CREATE TABLE  CAMION 
   (	"ID" NUMBER(8,2), 
	"EMPRESA_FK" NUMBER(8,2), 
	"PLACA" VARCHAR2(7 BYTE), 
	"MARCA" VARCHAR2(50 BYTE), 
	"MODELO" VARCHAR2(50 BYTE), 
	"ANO" VARCHAR2(50 BYTE), 
	"TIPO" VARCHAR2(50 BYTE), 
	"PESO" NUMBER(8,2), 
	"LARGO" NUMBER(8,2), 
	"ANCHO" NUMBER(8,2), 
	"ALTO" NUMBER(8,2), 
	"ESTADO" VARCHAR2(50 BYTE), 
	"CIUDAD" VARCHAR2(50 BYTE)
   )   ;
--------------------------------------------------------
--  DDL for Table CARGA
--------------------------------------------------------

  CREATE TABLE  CARGA 
   (	"ID" NUMBER(8,2), 
	"DESCRIPCION" VARCHAR2(350 BYTE), 
	"VALOR" NUMBER(15,2), 
	"PESO" NUMBER(8,2), 
	"LARGO" NUMBER(8,2), 
	"ANCHO" NUMBER(8,2), 
	"ALTO" NUMBER(8,2), 
	"EMBALAJE" VARCHAR2(50 BYTE), 
	"TIPO" VARCHAR2(50 BYTE), 
	"PEDIDO_FK" NUMBER(8,2)
   )   ;
--------------------------------------------------------
--  DDL for Table CENTROACOPIO
--------------------------------------------------------

  CREATE TABLE  CENTROACOPIO 
   (	"ID" NUMBER(8,2), 
	"CIUDAD" VARCHAR2(50 BYTE), 
	"DIRECCION" VARCHAR2(50 BYTE), 
	"UBICACION_CORD" VARCHAR2(50 BYTE), 
	"EMPRESA_FK" NUMBER(8,2), 
	"NUMERO_AUXILIARES" NUMBER(3,0)
   )   ;
--------------------------------------------------------
--  DDL for Table CLIENTE
--------------------------------------------------------

  CREATE TABLE  CLIENTE 
   (	"ID" NUMBER(8,2), 
	"USUARIO_FK" NUMBER(8,2), 
	"CUENTA_FK" NUMBER(8,2), 
	"NIT" VARCHAR2(50 BYTE)
   )   ;
--------------------------------------------------------
--  DDL for Table CUENTAS
--------------------------------------------------------

  CREATE TABLE  CUENTAS 
   (	"ID" NUMBER(8,2), 
	"NUMERO" VARCHAR2(50 BYTE), 
	"BANCO" VARCHAR2(50 BYTE), 
	"TARJETA_CREDITO" VARCHAR2(50 BYTE), 
	"FECHA_VENCIMIENTO" DATE, 
	"CODIGO_SEGURIDAD" VARCHAR2(3 BYTE), 
	"NOMBRETARJETA" VARCHAR2(50 BYTE)
   )   ;

   COMMENT ON COLUMN  CUENTAS."TARJETA_CREDITO" IS 'Master, Visa, American, etc.';;
--------------------------------------------------------
--  DDL for Table EMPRESA
--------------------------------------------------------

  CREATE TABLE  EMPRESA 
   (	"ID" NUMBER(8,2), 
	"NIT" VARCHAR2(50 BYTE), 
	"ASEGURADORA" VARCHAR2(50 BYTE), 
	"VALOR_MAXIMO_SEGURO" NUMBER(8,2), 
	"VALOR_MINIMO_SEGURO" NUMBER(8,2), 
	"NUMERO_CUENTA" VARCHAR2(50 BYTE), 
	"BANCO" VARCHAR2(50 BYTE), 
	"USUARIO_FK" NUMBER(8,2), 
	"CALIFICACION" NUMBER(8,0)
   )   ;
--------------------------------------------------------
--  DDL for Table PEDIDO
--------------------------------------------------------

  CREATE TABLE  PEDIDO 
   (	"ID" NUMBER(8,2), 
	"EMPRESA_FK" NUMBER(8,2), 
	"CLIENTE_FK" NUMBER(8,2), 
	"FECHA_HORA_SALIDA" TIMESTAMP (6), 
	"FECHA_HORA_LLEGADA" TIMESTAMP (6), 
	"LUGAR_CARGA_CORD" VARCHAR2(50 BYTE), 
	"LUGAR_DESCARGA_CORD" VARCHAR2(50 BYTE), 
	"LUGAR_CARGA_DIR" VARCHAR2(50 BYTE), 
	"LUGAR_DESCARGA_DIR" VARCHAR2(50 BYTE), 
	"LUGAR_CARGA_CIUDAD" VARCHAR2(50 BYTE), 
	"LUGAR_DESCARGA_CIUDAD" VARCHAR2(50 BYTE), 
	"ASEGURADA" VARCHAR2(50 BYTE), 
	"ESTADO" VARCHAR2(50 BYTE), 
	"PRECIO" NUMBER(8,2), 
	"AUXILIAR_CARGA" NUMBER(3,0), 
	"AUXILIAR_DESCARGA" NUMBER(3,0), 
	"HORA_PEDIDO" TIMESTAMP (6)
   )   ;
--------------------------------------------------------
--  DDL for Table TRANSPORTADOR
--------------------------------------------------------

  CREATE TABLE  TRANSPORTADOR 
   (	"ID" NUMBER(8,2), 
	"USUARIO_FK" NUMBER(8,2), 
	"EMPRESA_FK" NUMBER(8,2), 
	"NUMERO_LICENCIA" VARCHAR2(50 BYTE), 
	"CEDULA" VARCHAR2(50 BYTE), 
	"ESTADO" VARCHAR2(50 BYTE), 
	"CIUDAD" VARCHAR2(50 BYTE)
   )   ;
--------------------------------------------------------
--  DDL for Table TRAYECTO
--------------------------------------------------------

  CREATE TABLE  TRAYECTO 
   (	"PEDIDO_FK" NUMBER(8,2), 
	"TRANSPORTADOR_FK" NUMBER(8,2), 
	"CAMION_FK" NUMBER(8,2), 
	"LONGITUD" VARCHAR2(20 BYTE), 
	"LATITUD" VARCHAR2(20 BYTE), 
	"ESTADO" CHAR(1 BYTE), 
	"MONITOREO" CHAR(1 BYTE), 
	"ID" NUMBER(8,0)
   )   ;
--------------------------------------------------------
--  DDL for Table USUARIO
--------------------------------------------------------

  CREATE TABLE  USUARIO 
   (	"ID" NUMBER(8,2), 
	"USUARIO" VARCHAR2(150 BYTE), 
	"CLAVE" VARCHAR2(150 BYTE), 
	"NOMBRE" VARCHAR2(150 BYTE)
   )   ;
--------------------------------------------------------
--  DDL for Table VALORES
--------------------------------------------------------

  CREATE TABLE  VALORES 
   (	"ID" NUMBER(8,2), 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"VALOR" VARCHAR2(50 BYTE)
   )   ;
--------------------------------------------------------
--  DDL for Index IXFK_PEDIDO_CLIENTE
--------------------------------------------------------

  CREATE INDEX  IXFK_PEDIDO_CLIENTE ON  PEDIDO ("CLIENTE_FK") 
    ;
--------------------------------------------------------
--  DDL for Index TRAYECTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX  TRAYECTO_PK ON  TRAYECTO ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Empresa
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Empresa ON  EMPRESA ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_CLIENTE_USUARIO
--------------------------------------------------------

  CREATE INDEX  IXFK_CLIENTE_USUARIO ON  CLIENTE ("USUARIO_FK") 
    ;
--------------------------------------------------------
--  DDL for Index TRAYECTO_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX  TRAYECTO_UK1 ON  TRAYECTO ("PEDIDO_FK", "TRANSPORTADOR_FK", "CAMION_FK") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Pedido
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Pedido ON  PEDIDO ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_PEDIDO_EMPRESA
--------------------------------------------------------

  CREATE INDEX  IXFK_PEDIDO_EMPRESA ON  PEDIDO ("EMPRESA_FK") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_CARGA_PEDIDO
--------------------------------------------------------

  CREATE INDEX  IXFK_CARGA_PEDIDO ON  CARGA ("PEDIDO_FK") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_TRANSPORTADOR_USUARIO
--------------------------------------------------------

  CREATE INDEX  IXFK_TRANSPORTADOR_USUARIO ON  TRANSPORTADOR ("USUARIO_FK") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_CLIENTE_CUENTAS
--------------------------------------------------------

  CREATE INDEX  IXFK_CLIENTE_CUENTAS ON  CLIENTE ("CUENTA_FK") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_TRANSPORTADOR_EMPRESA
--------------------------------------------------------

  CREATE INDEX  IXFK_TRANSPORTADOR_EMPRESA ON  TRANSPORTADOR ("EMPRESA_FK") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Usuario
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Usuario ON  USUARIO ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_EMPRESA_USUARIO
--------------------------------------------------------

  CREATE INDEX  IXFK_EMPRESA_USUARIO ON  EMPRESA ("USUARIO_FK") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Transportador
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Transportador ON  TRANSPORTADOR ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Cliente
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Cliente ON  CLIENTE ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index PK_CentrosAcopio
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_CentrosAcopio ON  CENTROACOPIO ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Cuentas
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Cuentas ON  CUENTAS ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index IXFK_CENTROSACOPIO_EMPRESA
--------------------------------------------------------

  CREATE INDEX  IXFK_CENTROSACOPIO_EMPRESA ON  CENTROACOPIO ("EMPRESA_FK") 
    ;
--------------------------------------------------------
--  DDL for Index PK_CARGA
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_CARGA ON  CARGA ("ID") 
    ;
--------------------------------------------------------
--  DDL for Index PK_Camion
--------------------------------------------------------

  CREATE UNIQUE INDEX  PK_Camion ON  CAMION ("ID") 
    ;
--------------------------------------------------------
--  Constraints for Table CENTROACOPIO
--------------------------------------------------------

  ALTER TABLE  CENTROACOPIO ADD CONSTRAINT "PK_CentrosAcopio" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  CENTROACOPIO MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USUARIO
--------------------------------------------------------

  ALTER TABLE  USUARIO ADD CONSTRAINT "PK_Usuario" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  USUARIO MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE  USUARIO ADD UNIQUE ("USUARIO")
  USING INDEX   ENABLE;
--------------------------------------------------------
--  Constraints for Table CLIENTE
--------------------------------------------------------

  ALTER TABLE  CLIENTE ADD CONSTRAINT "PK_Cliente" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  CLIENTE MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CAMION
--------------------------------------------------------

  ALTER TABLE  CAMION ADD CONSTRAINT "PK_Camion" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  CAMION MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TRAYECTO
--------------------------------------------------------

  ALTER TABLE  TRAYECTO ADD CONSTRAINT "TRAYECTO_UK1" UNIQUE ("PEDIDO_FK", "TRANSPORTADOR_FK", "CAMION_FK")
  USING INDEX   ENABLE;
  ALTER TABLE  TRAYECTO ADD CONSTRAINT "TRAYECTO_PK" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  TRAYECTO MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE  TRAYECTO MODIFY ("CAMION_FK" NOT NULL ENABLE);
  ALTER TABLE  TRAYECTO MODIFY ("TRANSPORTADOR_FK" NOT NULL ENABLE);
  ALTER TABLE  TRAYECTO MODIFY ("PEDIDO_FK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPRESA
--------------------------------------------------------

  ALTER TABLE  EMPRESA ADD CONSTRAINT "PK_Empresa" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  EMPRESA MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CUENTAS
--------------------------------------------------------

  ALTER TABLE  CUENTAS ADD CONSTRAINT "PK_Cuentas" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  CUENTAS MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TRANSPORTADOR
--------------------------------------------------------

  ALTER TABLE  TRANSPORTADOR ADD CONSTRAINT "PK_Transportador" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  TRANSPORTADOR MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table VALORES
--------------------------------------------------------

  ALTER TABLE  VALORES ADD PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  VALORES MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CARGA
--------------------------------------------------------

  ALTER TABLE  CARGA ADD CONSTRAINT "PK_CARGA" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  CARGA MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PEDIDO
--------------------------------------------------------

  ALTER TABLE  PEDIDO ADD CONSTRAINT "PK_Pedido" PRIMARY KEY ("ID")
  USING INDEX   ENABLE;
  ALTER TABLE  PEDIDO MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CAMION
--------------------------------------------------------

  ALTER TABLE  CAMION ADD CONSTRAINT "FK_Camion_Transportador" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES  EMPRESA ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CARGA
--------------------------------------------------------

  ALTER TABLE  CARGA ADD CONSTRAINT "FK_CARGA_Pedido" FOREIGN KEY ("PEDIDO_FK")
	  REFERENCES  PEDIDO ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CENTROACOPIO
--------------------------------------------------------

  ALTER TABLE  CENTROACOPIO ADD CONSTRAINT "FK_CentrosAcopio_Empresa" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES  EMPRESA ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CLIENTE
--------------------------------------------------------

  ALTER TABLE  CLIENTE ADD CONSTRAINT "FK_Cliente_Cuentas" FOREIGN KEY ("CUENTA_FK")
	  REFERENCES  CUENTAS ("ID") ENABLE;
  ALTER TABLE  CLIENTE ADD CONSTRAINT "FK_Cliente_Usuario" FOREIGN KEY ("USUARIO_FK")
	  REFERENCES  USUARIO ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPRESA
--------------------------------------------------------

  ALTER TABLE  EMPRESA ADD CONSTRAINT "FK_Empresa_Usuario" FOREIGN KEY ("USUARIO_FK")
	  REFERENCES  USUARIO ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PEDIDO
--------------------------------------------------------

  ALTER TABLE  PEDIDO ADD CONSTRAINT "FK_Pedido_Cliente" FOREIGN KEY ("CLIENTE_FK")
	  REFERENCES  CLIENTE ("ID") ENABLE;
  ALTER TABLE  PEDIDO ADD CONSTRAINT "FK_Pedido_Empresa" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES  EMPRESA ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TRANSPORTADOR
--------------------------------------------------------

  ALTER TABLE  TRANSPORTADOR ADD CONSTRAINT "FK_Transportador_Empresa" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES  EMPRESA ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE  TRANSPORTADOR ADD CONSTRAINT "FK_Transportador_Usuario" FOREIGN KEY ("USUARIO_FK")
	  REFERENCES  USUARIO ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TRAYECTO
--------------------------------------------------------

  ALTER TABLE  TRAYECTO ADD CONSTRAINT "TRAYECTO_FK1" FOREIGN KEY ("CAMION_FK")
	  REFERENCES  CAMION ("ID") ENABLE;
  ALTER TABLE  TRAYECTO ADD CONSTRAINT "TRAYECTO_FK2" FOREIGN KEY ("PEDIDO_FK")
	  REFERENCES  PEDIDO ("ID") ENABLE;
  ALTER TABLE  TRAYECTO ADD CONSTRAINT "TRAYECTO_FK3" FOREIGN KEY ("TRANSPORTADOR_FK")
	  REFERENCES  TRANSPORTADOR ("ID") ENABLE;
