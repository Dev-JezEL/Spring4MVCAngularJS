'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,nombre:'',entidad:'',municipio:''};
    self.repetido;
    self.numero;
    self.repetido2;
    self.numero2;
    self.users=[];

    self.submit = submit;
    self.submitRepetido = submitRepetido;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllUsers();
    
    function submitRepetidoPOST(){
    	alert("entro");
    	
    }
    
    function submitRepetido(){
    	//alert("entro");
    	UserService.masRepetido()      
    		.then(
            function(d) {
            	console.log("data=>" + d);
                self.repetido = d.nombre;
                self.numero = d.cantidad;
                console.log("repetido=>" + self.repetido);
                alert("Nombre mas repetido: "+self.repetido+ " veces: "+self.numero);
            },
            function(errResponse){
                console.error('Error');
            }
        );
    	
    }

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error mientras obtenia usuarios');
            }
        );
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error mientras creaba usuario');
            }
        );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error mientras actualizaba usuario');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error mientras eliminaba usuario');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Guardando nuevo usuario', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('Actualizando usuario con id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id editando', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id eliminando', id);
        if(self.user.id === id) {
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,nombre:'',entidad:'',municipio:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    


}]);
