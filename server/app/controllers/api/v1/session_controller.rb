module Api
  module V1
    class SessionController < ApplicationController

      #use it on controllers where need to auth before access to api
      # acts_as_token_authentication_handler_for User 

      skip_before_filter :verify_authenticity_token
      respond_to :json      
      
      def create
        load_user
        
        if @user.nil?
          render json: { message: "User not found" }, status: 404
        else
          sign_in_user
        end
      end
      
    private

      def load_user
        @user = User.find_for_database_authentication(email: params[:email])
      end

      def sign_in_user
        if @user.valid_password? params[:password]
          sign_in("user", @user)
        else 
          render json: {message: "Email or password are wrong"}, status: 401
        end        
      end
    end
    
  end
end