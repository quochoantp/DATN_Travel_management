import axiosIns from 'axios'

export const state = () => ({
  dataDashboard: null,
  dataDashboardFilter: null,
})
const getters = {
  getDataDashboard: (state) => state.dataDashboard,
  getDataDashboardFilter: (state) => state.dataDashboardFilter,
}
const mutations = {
  setDataDashboard: (state, payload) => {
    state.dataDashboard = payload
  },
  setDataDashboardFilter: (state, payload) => {
    state.dataDashboardFilter = payload
  },
}
const actions = {
  async actionGetDataDashBoard({ commit }, year) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(`
    http://localhost:8091/api/v1/dashboard/get-data/${year}`)
      commit('setDataDashboard', data.data)
  },
  async actionGetDataDashBoardFilter({ commit }, objectSearch) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(`
    http://localhost:8091/api/v1/dashboard/get-data-filter?month=${objectSearch.month}&year=${objectSearch.year}`)
      commit('setDataDashboardFilter', data.data)
  },
}
export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
}