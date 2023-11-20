USE `supermercado`;
SELECT 
REP.id_repositor,
PD.id_producto,
PD.nombre,
GDL.nombre,
PR.desc_presentacion
FROM repositor AS REP
JOIN gondola_producto_repositor AS GPR ON REP.id_repositor = GPR.id_repositor
JOIN gondola AS GDL ON GDL.id_gondola = GPR.id_gondola
JOIN gondola_producto AS GP ON GP.id_gondola = GDL.id_gondola
JOIN presentacion AS PR ON GP.id_presentacion = PR.id_presentacion
JOIN producto AS PD ON GP.id_producto = PD.id_producto
WHERE REP.id_repositor = 2;