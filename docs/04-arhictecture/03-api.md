# API

## Функции (эндпониты)

1. CRUDS (create, read, update, delete, search) для объявлений-заказов и предложений товаров/услуг
2. CRUDS для пользователей/продавцов

## Описание сущности Offer (Предложение)

1. Info
    1. Title (Наименование)
    2. Description (Описание - цена, характеристики, медиа)
    3. Owner (Продавец/Поставщик)
    4. Media
2. DealSide: Demand/Proposal
3. ProductType (Категория)
4. ProductId - идентификатор/артикул

## Описание сущности Пользователь

1. Info
   1. Name
   2. Contacts
   3. Avatar
2. Cart - корзина
3. Offers - предложения пользователя
4. Id