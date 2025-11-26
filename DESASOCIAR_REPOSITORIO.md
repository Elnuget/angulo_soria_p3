# Cómo Desasociar un Repositorio Git

Este documento describe los pasos para desasociar un repositorio local de su remote original y conectarlo a una nueva cuenta de GitHub.

## Comandos Utilizados

### 1. Verificar el remote actual
```bash
git remote -v
```
Este comando muestra los repositorios remotos configurados actualmente.

### 2. Eliminar el remote existente
```bash
git remote remove origin
```
Este comando elimina la conexión con el repositorio remoto original.

### 3. Verificar que se eliminó correctamente
```bash
git remote -v
```
Verifica que no haya ningún remote configurado (no debería mostrar nada).

### 4. Agregar el nuevo remote (tu cuenta)
```bash
git remote add origin https://github.com/TU_USUARIO/TU_REPOSITORIO.git
```
Reemplaza `TU_USUARIO` y `TU_REPOSITORIO` con tu información de GitHub.

### 5. Subir el código a tu repositorio
```bash
git push -u origin main
```
O si tu rama principal se llama `master`:
```bash
git push -u origin master
```

## Resumen

1. ✅ Verificar remote actual
2. ✅ Eliminar remote origin
3. ✅ Crear nuevo repositorio en GitHub
4. ✅ Agregar nuevo remote
5. ✅ Push al nuevo repositorio

---

**Nota:** Asegúrate de crear primero el repositorio en GitHub antes de ejecutar el paso 4.
