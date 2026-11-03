import csv
import json
from collections import OrderedDict

# 1) Abrimos el CSV y lo leemos fila por fila
cotizaciones = OrderedDict()

with open('dataset_mineria_cotizaciones.csv', newline='', encoding='utf-8-sig') as archivo:
    lector = csv.DictReader(archivo)  # convierte cada fila en un diccionario

    for fila in lector:
        cid = fila['cotizacion_id'].strip()

        # 2) Si es la PRIMERA vez que vemos esta cotizacion, creamos su "carpeta"
        if cid not in cotizaciones:
            cotizaciones[cid] = {
                '_id': cid,
                'cliente': fila['cliente'].strip(),
                'fechaCotizacion': fila['fecha_cotizacion'].strip(),
                'valorTotal': float(fila['costo_total_cotizacion']),
                'estado': 'PENDIENTE',
                'productos': []
            }

        # 3) Cada fila agrega UN producto dentro de esa carpeta
        cotizaciones[cid]['productos'].append({
            'nombre': fila['producto'].strip(),
            'cantidad': float(fila['cantidad']),
            'valorUnitario': float(fila['valor_unitario']),
            'valorTotalProducto': float(fila['valor_total_producto'])
        })

# 4) Guardamos todas las carpetas en un solo archivo JSON
with open('cotizaciones.json', 'w', encoding='utf-8') as archivo:
    json.dump(list(cotizaciones.values()), archivo, ensure_ascii=False, indent=2)

print(f'✅ Listo: se generaron {len(cotizaciones)} cotizaciones en cotizaciones.json')