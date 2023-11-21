USE `supermercado`;
SELECT 
ST.id_sector,
PD.id_producto,
PD.nombre_producto,
GDL.nombre_gondola,
PR.desc_presentacion
FROM producto as PD
JOIN gondola_producto as GP ON GP.id_producto = PD.id_producto
JOIN presentacion as PR ON GP.id_presentacion = PR.id_presentacion
JOIN gondola as GDL ON GP.id_gondola = GDL.id_gondola
JOIN sector as ST ON GDL.id_sector = ST.id_sector
WHERE ST.id_sector = 13;
;

