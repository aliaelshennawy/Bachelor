Rails.application.routes.draw do

    # This route sends requests to our naked url to the *cool* action in the *gif* controller.
 
 get '/signup' => 'users#new'
    post '/users' => 'users#create'
get '/users' => 'users#index'
 
  # these routes are for showing users a login form, logging them in, and logging them out.
  get '/login' => 'sessions#new'
  post '/login' => 'sessions#create'
  get '/logout' => 'sessions#destroy'
  get '/problems' => 'problems#index'
  get '/signup' => 'users#new'
  post '/users' => 'users#create'
  #post '/imgupload' => 'problems#create'
  get '/problems/get_audio' => 'problems#index_audio'
  get '/problems/get_photo' => 'problems#index_photo'
  get '/replies' => 'replies#index' #Get all replies for a specific question
  get '/reply' => 'replies#show' #Show a specific reply
  post '/replyCreate' => 'replies#create' #Create Reply
  get '/advices' => 'advices#index'
  get '/advice' => 'advices#show'
  post '/adviceCreate' => 'advices#create'
 # post '/problems' => 'problems#create'
  #post 
  post '/problems/:user_id' =>'problems#create'
 #post '/problems' => 'problems#create'
#resources :problems, only: [:new, :create, :index, :destroy
 #get '/Problems'
# root to: 'problems#index'
# namespace :api , defaults: {format: 'json'} do
#   scope module: :v1 do
#     post "/problem/new_problem" => 'problems#create'


# post '/pictures'

end
  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

