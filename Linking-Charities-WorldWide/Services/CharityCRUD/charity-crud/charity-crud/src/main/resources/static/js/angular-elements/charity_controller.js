'use strict';

App.controller('CharityController', ['$scope', 'Charity', function($scope, Charity) {
    var self = this;
    self.charity= new Charity();

    self.charities=[];

    self.fetchAllCharities = function(){
        self.charities = Charity.query();
    };

    self.createCharity = function(){
        self.charity.$save(function(){
            self.fetchAllCharities();
        });
    };

    self.updateCharity = function(){
        self.charity.$update(function(){
            self.fetchAllCharities();
        });
    };

    self.deleteCharity = function(identity){
        var charity = Charity.get({id:identity}, function() {
            charity.$delete(function(){
                console.log('Deleting charity with id ', identity);
                self.fetchAllCharities();
            });
        });
    };

    self.fetchAllCharities();

    self.submit = function() {
        if(self.charity.id==null){
            console.log('Saving New Charity', self.charity);
            self.createCharity();
        }else{
            console.log('Updatingcharity with id ', self.charity.id);
            self.updateCharity();
            console.log('Charity updated with id ', self.charity.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.charities.length; i++){
            if(self.charities[i].id === id) {
                self.charity = angular.copy(self.charities[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.charity.id === id) {//If it is the one shown on screen, reset screen
            self.reset();
        }
        self.deleteCharity(id);
    };


    self.reset = function(){
        self.charity= new Charity();
        $scope.myForm.$setPristine(); //reset Form
    };

}]);
