<template>
  <div>
    <router-view />
  </div>
</template>

<style>
.background {
  display: flex;
  justify-content: center;
}

.body {
  width: 390px;
  height: 844px;
  background-color: #f2f2f7;
}

.footer {
  position: fixed;
  width: 100%;
  width: 390px;
  height: 73px;
  bottom: 0;
  background: white;
}

.title {
  text-align: center;
  font-size: 40px;
}
</style>
