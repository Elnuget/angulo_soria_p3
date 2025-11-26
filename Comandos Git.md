# 🔧 Reconfiguración del Repositorio Git

Este documento describe el proceso completo de desasociación del repositorio original, asociación a un nuevo repositorio propio y los comandos esenciales para clonar, realizar commits y subir cambios.

---

## 📌 1. Desasociación del Remote Original y Configuración del Nuevo

El usuario eliminó el remote existente y configuró su propio repositorio en GitHub.

### 🔍 Comandos ejecutados

```bash
# Verificar el remote actual
git remote -v

# Eliminar el remote anterior
git remote remove origin

# Agregar el nuevo repositorio como origin
git remote add origin [https://github.com/Elnuget/angulo_soria_p3.git](https://github.com/Elnuget/angulo_soria_p3.git)

# Subir el proyecto al nuevo repositorio
git push -u origin main
```

## 📌 2. Clonar el Repositorio

Para obtener una copia local del proyecto desde GitHub:

```bash
# Para obtener una copia local del proyecto desde GitHub:
git clone [https://github.com/Elnuget/angulo_soria_p3.git](https://github.com/Elnuget/angulo_soria_p3.git)
# Ingresar al directorio del proyecto
cd angulo_soria_p3
```
## 📌 3. Realizar Cambios: Commit y Push

```bash
# Agregar cambios
git add .
# Crear un commit con mensaje
git commit -m "Mensaje del commit"
# Subir los cambios al repositorio remoto
git push
```

## Resultado Final

El proyecto quedó publicado correctamente en GitHub:

https://github.com/Elnuget/angulo_soria_p3