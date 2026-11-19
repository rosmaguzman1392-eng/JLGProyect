import json
import random
from datetime import date, timedelta

random.seed(42)  # Semilla fija: mismo resultado siempre (reproducibilidad académica)

CLIENTES = [
    "HOTEL HILTON", "HOTEL LAS AMERICAS", "HOTEL RADISSON", "HOTEL VIAS DEL MAR",
    "HOTELES VIAS DEL MAR", "DELTA INGENIERIA SA", "GML COLLECTION SAN LAZARO",
    "CALIDAD INTEGRAL CTG SAS", "MORROS VITRS", "HOTEL ESTELAR CARTAGENA",
    "AEROPUERTO RAFAEL NUNEZ", "CLINICA BOCAGRANDE", "HOTEL ALMIRANTE",
    "INDUSTRIAS QUIMICAS DEL CARIBE", "HOTEL MONTERREY", "EDIFICIO IOVICH",
]

PRODUCTOS = {
    "Mts Lineales": 65000, "Codos": 52000, "Tes": 46000, "Valvulas": 118000,
    "Poliuretano A Kg": 38000, "Chaquetas de aluminio": 95000,
    "Difusores": 74000, "Codos o curvas": 52000,
}

# Pesos: más pendientes y aprobadas que rechazadas (realista para JLG)
ESTADOS = ["PENDIENTE"] * 5 + ["APROBADA"] * 3 + ["COMPLETADA"] * 1 + ["RECHAZADA"] * 1

def fecha_aleatoria():
    inicio = date(2024, 1, 1)
    fin = date(2026, 10, 31)
    return inicio + timedelta(days=random.randint(0, (fin - inicio).days))

docs = []
for i in range(1, 1911):  # ← exactamente 1910 documentos
    cliente = random.choice(CLIENTES)
    f = fecha_aleatoria()
    productos = []
    for nombre in random.sample(list(PRODUCTOS), k=random.randint(2, 6)):
        cantidad = round(random.uniform(0.5, 60), 1)
        vu = float(PRODUCTOS[nombre])
        productos.append({
            "nombre": nombre,
            "cantidad": cantidad,
            "valorUnitario": vu,
            "valorTotalProducto": round(cantidad * vu, 2),
        })
    tag = cliente.replace(" ", "")
    docs.append({
        "_id": f"COTIZACION {tag}{f.strftime('%d%m%y')}-{i:04d}",  # único gracias a -0001...-1910
        "cliente": cliente,
        "fechaCotizacion": f.isoformat(),
        "valorTotal": round(sum(p["valorTotalProducto"] for p in productos), 2),
        "estado": random.choice(ESTADOS),
        "productos": productos,
    })

with open("cotizaciones_1910.json", "w", encoding="utf-8") as fh:
    json.dump(docs, fh, ensure_ascii=False, indent=2)

print(f"✅ Listo: {len(docs)} registros en cotizaciones_1910.json")