

import { createApp } from "vue";
import router from "./router";
import store from "./store";
import App from "./App.vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle.js";

createApp(App).use(router).use(store).mount("#app");
