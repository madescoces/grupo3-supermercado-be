USE `supermercado`;
SELECT 
ST.id_sector,
PD.id_producto,
PD.nombre,
GDL.nombre,
PR.desc_presentacion
FROM gondola as GDL
JOIN gondola_producto as GP ON GDL.id_gondola = GP.id_gondola
JOIN producto as PD ON GP.id_producto = PD.id_producto
JOIN presentacion as PR ON GP.id_presentacion = PR.id_presentacion
JOIN sector as ST ON GDL.id_sector = ST.id_sector
WHERE ST.id_sector = 2;
;

