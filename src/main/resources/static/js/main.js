// Определяем новый компонент под именем todo-item
Vue.component('todo-item', {
    template: '<li>Это одна задача в списке</li>'
})

var app = new Vue({
    el: '#app',
    data: {
        message: 'Привет, Vue!'
    }
})