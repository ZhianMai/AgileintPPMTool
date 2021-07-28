import React, { Component } from "react";

class AddProject extends Component {
  constructor() {
    super();

    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      start_date: "",
      end_date: "",
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    // this.setState({ projectName: e.target.value });
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    // Prevent refreshing the page.
    e.preventDefault();

    const newProject = {
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };

    console.log(newProject);
  }

  render() {
    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Project form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Project Name"
                      /* This field name must match the Spring project name! */
                      name="projectName"
                      value={this.setState.projectName}
                      // No need to write this since binding done in ctor
                      // onChange={this.onChange.bind(this)}
                      onChange={this.onChange}
                    />
                  </div>
                  <br />
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Unique Project ID"
                      /* This field name must match the Spring project name! */
                      name="projectIdentifier"
                      value={this.state.projectIdentifier}
                      onChange={this.onChange}
                    />
                  </div>
                  {/*
                        <!-- disabled for Edit Only!! remove "disabled" for the Create operation -->
                        */}
                  <br />
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg"
                      placeholder="Project Description"
                      /* This field name must match the Spring project name! */
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    ></textarea>
                  </div>
                  <br />
                  <h5>Start Date:</h5>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      /* This field name must match the Spring project name! */
                      name="start_date"
                      value={this.state.start_date}
                      onChange={this.onChange}
                    />
                  </div>
                  <br />
                  <h5>Estimated End Date:</h5>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      /* This field name must match the Spring project name! */
                      name="end_date"
                      value={this.state.end_date}
                      onChange={this.onChange}
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default AddProject;
