# 🧪 La Tienda de Pociones Malditas (The Cursed Potion Shop)

## 📜 Descripción del Proyecto
Bienvenido a la gestión de **"El Caldero Burbujeante"**, la tienda de alquimia más cuestionable del reino. Este sistema está diseñado para gestionar el inventario de brebajes mágicos, desde simples curas para la gripe hasta elixires prohibidos que podrían transformar al cliente en un sapo.

El objetivo del sistema es permitir al Alquimista Jefe (el usuario) administrar sus creaciones, manteniendo un control estricto sobre la peligrosidad de los productos para evitar... "incidentes" con el Ministerio de Magia.

---

## 🎯 Objetivos del Sistema

1.  **Centralizar el Inventario:** Mantener un registro actualizado de todas las pociones existentes en la tienda.
2.  **Garantizar la Seguridad (Automática):** El sistema debe actuar como un "filtro de seguridad", detectando automáticamente mezclas demasiado inestables y marcándolas como ilegales antes de que salgan a la venta.
3.  **Gestión Ágil:** Permitir la creación rápida de nuevos experimentos y la eliminación de evidencia (borrado de productos).

---

## 📦 Entidad Principal: La Poción

Cada producto en el sistema debe representar una Poción con, al menos, las siguientes características informativas:

* **Nombre:** Identificador comercial del brebaje.
* **Efecto:** Descripción breve de lo que le hace al consumidor.
* **Nivel de Riesgo:** Un valor numérico (escala 1-100) que determina la volatilidad y peligro de la mezcla.
* **Estado Legal (Prohibida/Legal):** Indicador de si la venta de este artículo está permitida por la ley mágica vigente.

---

## ⚙️ Reglas de Negocio (Lógica del Dominio)

El sistema debe hacer cumplir estrictamente las siguientes reglas sin intervención manual:

### 1. La Regla de Seguridad Pública
Si una poción es creada con un **Nivel de Riesgo superior a 90**:
* El sistema debe marcarla **automáticamente** como **Prohibida (Ilegal)**.
* Esta acción es irreversible durante la creación: la seguridad prevalece sobre la intención del usuario.

### 2. Visibilidad de Advertencia
Las pociones marcadas como **Prohibidas** deben ser claramente distinguibles en el listado general (visualización de alerta) para evitar que los dependientes las vendan por error.

---

## 🚀 Requisitos Funcionales

### A. Módulo de Catálogo (Visualización)
* El sistema debe mostrar un listado completo de todas las pociones en stock.
* Debe permitir identificar rápidamente el nombre, efecto, riesgo y legalidad de cada ítem.
* Debe resaltar visualmente los ítems peligrosos/ilegales.

### B. Módulo del Caldero (Creación)
* Debe existir una interfaz para registrar nuevas pociones.
* El usuario debe poder ingresar el nombre, efecto y nivel de riesgo deseado.
* Al procesar la creación, se deben aplicar las **Reglas de Negocio** antes de guardar el ítem en el inventario.

### C. Módulo de Limpieza (Eliminación)
* El sistema debe permitir eliminar pociones del inventario (útil para cuando llega una inspección y hay que deshacerse de la evidencia).

### D. Navegación
* El usuario debe poder moverse fluidamente entre el Catálogo y el Caldero a través de un menú de navegación unificado.