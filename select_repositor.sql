USE `supermercado`;
SELECT 
REP.id_repositor,
PD.id_producto,
PD.nombre_producto,
GPR.*,
GDL.nombre_gondola,
PR.desc_presentacion
FROM producto AS PD
JOIN gondola_producto AS GP ON PD.id_producto = GP.id_producto
JOIN gondola AS GDL ON GDL.id_gondola = GP.id_gondola
JOIN presentacion AS PR ON PR.id_presentacion = GP.id_presentacion
JOIN gondola_producto_repositor AS GPR ON GPR.id_gondola = GP.id_gondola AND GPR.id_producto = GP.id_producto
JOIN repositor as REP ON REP.id_repositor = GPR.id_repositor
WHERE REP.id_repositor = 1
;