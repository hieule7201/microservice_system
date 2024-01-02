import { createStore } from "vuex";

import * as mutations from "./mutations";
import * as actions from "./actions/index.js";
import * as getters from "./getters";
import state from "./state";

const store = createStore({
  state,
  mutations,
  actions,
  getters,
});

export default store;
