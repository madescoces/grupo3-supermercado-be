USE `supermercado`;
SELECT 
GPR.id_gpr,
GPR.fecha,
PD.nombre_producto,
PD.desc_producto,
PD.id_producto_reemplazo,
PR.desc_presentacion,
FL.desc_fila,
GDL.nombre_gondola,
ST.desc_sector,
REP.nombre_repositor,
EMP.razon_social,
EMP.domicilio
FROM producto as PD
JOIN fila_producto AS FL ON FL.id_fila = PD.id_fila
JOIN gondola_producto as GP ON GP.id_producto = PD.id_producto
JOIN presentacion as PR ON GP.id_presentacion = PR.id_presentacion
JOIN gondola AS GDL ON GDL.id_gondola = GP.id_gondola
JOIN sector as ST ON GDL.id_sector = ST.id_sector
JOIN gondola_producto_repositor AS GPR ON GPR.id_gondola = GP.id_gondola AND GPR.id_producto = GP.id_producto
JOIN repositor as REP ON REP.id_repositor = GPR.id_repositor
JOIN empresa as EMP ON EMP.id_empresa = REP.id_empresa;







