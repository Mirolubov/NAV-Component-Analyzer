# NAV-Component-Analyzer
Component Analyzer for Navision c/al code

## ENU
We are developing in Navision 2018 using SOA component model.<br>
This means we distinguish separate "components" while design phase (low Coupling).<br>
Objects related to one component are marked with a component tag in object Version property. Format [Tag].<br>
A "component" can contain interface modules to provide other components with access to its functionality (Format IObject).
And adapter modules to provide a call to functions of interfaces of others components (Format AObject).

This program is intended for:
1. Pull out "components" from export object file (txt format).
2. Detect coupling of "components" for highlighting design errors.
3. Visualization of the obtained result.

## RUS
Мы ведем разработку в Navision 2018 используя подход SOA.<br>
Это означает что при проектировании выделяются "компоненты" независимые друг от друга (с низкой связанностью).<br>
Объекты относящиеся к одному компоненту помечаются тегом компонента в свойстве объекта "Версия" в формате [Tag].<br>
Компонент может содержать интерфейсные модули, для предоставления доступа других компонент к своему функционалу (Формат IObject).<br>
И модули адаптеры, обеспечивающие вызов функций интерфесов других компонент (в формате AObject). <br>

Данная программа предназначена для: 
1. определения "компонент" из файла с выгруженными объектами в формате txt.
2. Выявление зависимостей "компонент" для определения ошибок разработки.
3. Визуализации полученного результата.