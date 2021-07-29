import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import classnames from "classnames";

class AddProject extends Component {
  constructor() {
    super();

    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      start_date: "",
      end_date: "",
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  // Life cycle hooks
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
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

    // console.log(newProject);
    this.props.createProject(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;

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
                      /*
                       * class "form-control form-control-lg" is always render.
                       * class "is-invalid" renders if has error.
                       */
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.projectName,
                      })}
                      placeholder="Project Name"
                      /* This field name must match the Spring project name! */
                      name="projectName"
                      value={this.setState.projectName}
                      // No need to write this since binding done in ctor
                      // onChange={this.onChange.bind(this)}
                      onChange={this.onChange}
                    />
                    {/* <p>{errors.projectName}</p> */}
                    {errors.projectName && (
                      <div className="invalid-feedback">
                        {errors.projectName}
                      </div>
                    )}
                  </div>
                  <br />
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.projectIdentifier,
                      })}
                      placeholder="Unique Project ID"
                      /* This field name must match the Spring project name! */
                      name="projectIdentifier"
                      value={this.state.projectIdentifier}
                      onChange={this.onChange}
                    />
                    {/* <p>{errors.projectIdentifier}</p> */}
                    {errors.projectIdentifier && (
                      <div className="invalid-feedback">
                        {errors.projectIdentifier}
                      </div>
                    )}
                  </div>
                  {/*
                        <!-- disabled for Edit Only!! remove "disabled" for the Create operation -->
                        */}
                  <br />
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.description,
                      })}
                      placeholder="Project Description"
                      /* This field name must match the Spring project name! */
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    ></textarea>
                    {/* <p>{errors.description}</p> */}
                    {errors.description && (
                      <div className="invalid-feedback">
                        {errors.description}
                      </div>
                    )}
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

AddProject.propTypes = {
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createProject })(AddProject);
