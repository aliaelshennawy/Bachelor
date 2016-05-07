class AddAudioToProblem < ActiveRecord::Migration
  def change
    add_column :problems, :audio, :string
  end
end
